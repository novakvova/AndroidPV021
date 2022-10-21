using ApplicationCore.Entities.Identity;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ApplicationCore.Entities
{
    [Table("tblPosts")]
    public class PostEntity : BaseEntity<int>
    {
        [Required, StringLength(255)]
        public string Title { get; set; }
        [StringLength(4000)]
        public string Desctiption { get; set; }
        [ForeignKey("UserCreate")]
        public long UserCreateId { get; set; }
        public virtual AppUser UserCreate { get; set; }
        public virtual ICollection<UserPostSelect> UserPostSelects { get; set; }
    }
}
