using ApplicationCore.Entities;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;

namespace Infrastructure.Data
{
    public class ShopEFContext : DbContext
    {
        //ctor
        public ShopEFContext(DbContextOptions<ShopEFContext> options) : base(options)  { }
        
        public DbSet<CategoryEntity> Categories { get; set; }

        /*
        protected override void OnModelCreating(ModelBuilder builder)
        {
            base.OnModelCreating(builder);
            builder.ApplyConfigurationsFromAssembly(Assembly.GetExecutingAssembly());
        } */
    }
}
