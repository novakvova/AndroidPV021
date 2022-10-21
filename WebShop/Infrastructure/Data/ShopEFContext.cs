using ApplicationCore.Entities;
using ApplicationCore.Entities.Identity;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;

namespace Infrastructure.Data
{
    public class ShopEFContext : IdentityDbContext<AppUser, AppRole, long, IdentityUserClaim<long>,
        AppUserRole, IdentityUserLogin<long>, 
        IdentityRoleClaim<long>, IdentityUserToken<long>>
        //DbContext
    {
        //ctor
        public ShopEFContext(DbContextOptions<ShopEFContext> options) : base(options)  { }
        
        public DbSet<CategoryEntity> Categories { get; set; }
        /// <summary>
        /// List News
        /// </summary>
        public DbSet<PostEntity> Posts { get; set; }
        public DbSet<UserPostSelect> UserPostSelects { get; set; }


        protected override void OnModelCreating(ModelBuilder builder)
        {
            base.OnModelCreating(builder);
            builder.Entity<AppUserRole>(userRole =>
            {
                userRole.HasKey(ur => new { ur.UserId, ur.RoleId });

                userRole.HasOne(ur => ur.Role)
                    .WithMany(r => r.UserRoles)
                    .HasForeignKey(r => r.RoleId)
                    .IsRequired();

                userRole.HasOne(ur => ur.User)
                    .WithMany(r => r.UserRoles)
                    .HasForeignKey(r => r.UserId)
                    .IsRequired();
            });

            builder.Entity<UserPostSelect>(userPostSelect =>
            {
                userPostSelect.HasKey(ur => new { ur.UserId, ur.PostId });
            });
            //builder.ApplyConfigurationsFromAssembly(Assembly.GetExecutingAssembly());
        }
    }
}
