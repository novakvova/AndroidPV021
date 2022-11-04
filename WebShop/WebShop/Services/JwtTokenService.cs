using ApplicationCore.Entities.Identity;
using Microsoft.AspNetCore.Identity;
using Microsoft.IdentityModel.Tokens;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;

namespace WebShop.Services
{
    public interface IJwtTokenService
    {
        Task<string> GenerateTokenAsync(AppUser user);
    }

    public class JwtTokenService : IJwtTokenService
    {
        private readonly IConfiguration _config;
        private readonly UserManager<AppUser> _userManager;
        public JwtTokenService(IConfiguration config, UserManager<AppUser> userManager)
        {
            _config = config;
            _userManager = userManager;
        }

        public async Task<string> GenerateTokenAsync(AppUser user)
        {
            var roles = await _userManager.GetRolesAsync(user);
            List<Claim> claims = new List<Claim>()
            {
                new Claim("name", user.UserName)
            };

            foreach(var role in roles)
            {
                claims.Add(new Claim("roles", role));
            }
            var singinKey = new SymmetricSecurityKey(
                Encoding.UTF8.GetBytes(_config.GetValue<String>("JwtKey")));

            var myCredentials =
                new SigningCredentials(singinKey, SecurityAlgorithms.HmacSha256);

            var jwt = new JwtSecurityToken(
                signingCredentials: myCredentials,
                expires: DateTime.Now.AddDays(1000),
                claims: claims
                );

            return new JwtSecurityTokenHandler().WriteToken(jwt);
        }
    }
}
