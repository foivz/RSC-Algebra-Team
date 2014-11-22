using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using iDonor.Entities.Models.Base;

namespace iDonor.Entities.Models
{
    [Table("BloodType")]
    public partial class BloodType : IBaseEntity
    {
        public BloodType()
        {
            this.BloodStorages = new List<BloodStorage>();
            this.Donations = new List<Donation>();
        }


        [Column("ID")]
        public int ID { get; set; }

        [Column("Name")]
        public string Name { get; set; }
        public virtual ICollection<BloodStorage> BloodStorages { get; set; }
        public virtual ICollection<Donation> Donations { get; set; }
    }
}
