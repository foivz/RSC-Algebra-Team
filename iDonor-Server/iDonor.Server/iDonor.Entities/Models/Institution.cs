using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using iDonor.Entities.Models.Base;

namespace iDonor.Entities.Models
{
    [Table("Institution")]
    public partial class Institution : IBaseEntity
    {
        public Institution()
        {
            this.Donations = new List<Donation>();
            this.Users = new List<User>();
        }

 
        [Column("ID")] 
        public int ID { get; set; }
 
        [Column("Name")] 
        public string Name { get; set; }
 
        [Column("CreatedAt")] 
        public System.DateTime CreatedAt { get; set; }
 
        [Column("ModifiedAt")] 
        public System.DateTime ModifiedAt { get; set; }
 
        [Column("City")] 
        public string City { get; set; }
 
        [Column("Address")] 
        public string Address { get; set; }
 
        [Column("PostalNumber")] 
        public string PostalNumber { get; set; }
 
        [Column("PhoneNumber")] 
        public string PhoneNumber { get; set; }
 
        [Column("BloodStorageID")] 
        public int BloodStorageID { get; set; }
        public virtual BloodStorage BloodStorage { get; set; }
        public virtual ICollection<Donation> Donations { get; set; }
        public virtual ICollection<User> Users { get; set; }
    }
}
