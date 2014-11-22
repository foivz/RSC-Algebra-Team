using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using iDonor.Entities.Models.Base;

namespace iDonor.Entities.Models
{
    [Table("User")]
    public partial class User : IBaseEntity
    {
        public User()
        {
            this.Donations = new List<Donation>();
            this.MobileDevices = new List<MobileDevice>();
        }

 
        [Column("ID")] 
        public int ID { get; set; }
 
        [Column("Username")] 
        public string Username { get; set; }
 
        [Column("Password")] 
        public string Password { get; set; }
 
        [Column("Email")] 
        public string Email { get; set; }
 
        [Column("City")] 
        public string City { get; set; }
 
        [Column("Address")] 
        public string Address { get; set; }
 
        [Column("Sex")] 
        public string Sex { get; set; }
 
        [Column("Age")] 
        public int Age { get; set; }
 
        [Column("WeightKg")] 
        public Nullable<decimal> WeightKg { get; set; }
 
        [Column("PhoneNumber")] 
        public string PhoneNumber { get; set; }
 
        [Column("PostalNumber")] 
        public string PostalNumber { get; set; }
 
        [Column("TimeSinceLastDonationSec")] 
        public long TimeSinceLastDonationSec { get; set; }
 
        [Column("CreatedAt")] 
        public System.DateTime CreatedAt { get; set; }
 
        [Column("ModifiedAt")] 
        public System.DateTime ModifiedAt { get; set; }
 
        [Column("Active")] 
        public Nullable<bool> Active { get; set; }
 
        [Column("ProfilePicURL")] 
        public string ProfilePicURL { get; set; }
 
        [Column("RoleID")] 
        public int RoleID { get; set; }
 
        [Column("ManagesInstitutionID")] 
        public Nullable<int> ManagesInstitutionID { get; set; }
        public virtual ICollection<Donation> Donations { get; set; }
        public virtual Institution Institution { get; set; }
        public virtual ICollection<MobileDevice> MobileDevices { get; set; }
        public virtual Role Role { get; set; }
    }
}
