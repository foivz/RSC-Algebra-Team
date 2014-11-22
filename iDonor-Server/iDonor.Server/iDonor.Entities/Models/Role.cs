using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using iDonor.Entities.Models.Base;

namespace iDonor.Entities.Models
{
    [Table("Role")]
    public partial class Role : IBaseEntity
    {
        public Role()
        {
            this.Users = new List<User>();
        }

 
        [Column("ID")] 
        public int ID { get; set; }
 
        [Column("Name")] 
        public string Name { get; set; }
        public virtual ICollection<User> Users { get; set; }
    }
}
