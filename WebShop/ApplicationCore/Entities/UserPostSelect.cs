using ApplicationCore.Entities.Identity;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ApplicationCore.Entities
{
    [Table("tblUserPostSelects")]
    public class UserPostSelect
    {
        [ForeignKey("User")]
        public long UserId { get; set; }
        [ForeignKey("Post")]
        public int PostId { get; set; }
        public virtual AppUser User { get; set; }
        public virtual PostEntity Post { get; set; }
    }
}
