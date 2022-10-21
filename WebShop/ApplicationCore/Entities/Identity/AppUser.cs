using Microsoft.AspNetCore.Identity;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ApplicationCore.Entities.Identity
{
    public class AppUser : IdentityUser<long>
    {
        [StringLength(maximumLength: 150)]
        public string FirstName { get; set; }
        [StringLength(maximumLength: 150)]
        public string SecondName { get; set; }
        [StringLength(maximumLength: 150)]
        public string Photo { get; set; }
        public virtual ICollection<AppUserRole> UserRoles { get; set; }
        public virtual ICollection<UserPostSelect> UserPostSelects { get; set; }
        public virtual ICollection<PostEntity> Posts { get; set; }
    }
}
