using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using iDonor.Entities.Models.Base;

namespace iDonor.Entities.Models
{
    [Table("Donation")]
    public partial class Donation : IBaseEntity
    {
 
        [Column("ID")] 
        public int ID { get; set; }
 
        [Column("UserID")] 
        public Nullable<int> UserID { get; set; }
 
        [Column("CreatedAt")] 
        public System.DateTime CreatedAt { get; set; }
 
        [Column("InstitutionID")] 
        public int InstitutionID { get; set; }
 
        [Column("DonationTypeID")] 
        public int DonationTypeID { get; set; }
 
        [Column("Description")] 
        public string Description { get; set; }
 
        [Column("BloodTypeID")] 
        public int BloodTypeID { get; set; }
        public virtual BloodType BloodType { get; set; }
        public virtual DonationType DonationType { get; set; }
        public virtual Institution Institution { get; set; }
        public virtual User User { get; set; }
    }
}
