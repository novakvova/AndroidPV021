using ApplicationCore.Entities.Identity;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using WebShop.Models;
using WebShop.Services;

namespace WebShop.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class AccountController : ControllerBase
    {
        private readonly IJwtTokenService _jwtTokenService;
        private readonly UserManager<AppUser> _userManager;

        public AccountController(IJwtTokenService jwtTokenService, UserManager<AppUser> userManager)
        {
            _jwtTokenService = jwtTokenService;
            _userManager = userManager;
        }
        [HttpPost("login")]
        public async Task<IActionResult> login([FromBody] LoginViewModel model)
        {
            var user = await _userManager.FindByEmailAsync(model.Email);
            if(user == null)
                return BadRequest();
            if(await _userManager.CheckPasswordAsync(user, model.Password))
            {
                string token = await _jwtTokenService.GenerateTokenAsync(user);
                return Ok(new { token });
            }
            return BadRequest();
        }
        
    }
}
