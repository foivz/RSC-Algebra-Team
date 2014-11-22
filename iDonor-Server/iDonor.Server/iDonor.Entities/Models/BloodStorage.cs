using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using iDonor.Entities.Models.Base;

namespace iDonor.Entities.Models
{
    [Table("BloodStorage")]
    public partial class BloodStorage : IBaseEntity
    {
        public BloodStorage()
        {
            this.BloodLevelRules = new List<BloodLevelRule>();
            this.Institutions = new List<Institution>();
        }

 
        [Column("ID")] 
        public int ID { get; set; }
 
        [Column("BloodTypeID")] 
        public int BloodTypeID { get; set; }
 
        [Column("Quantity")] 
        public int Quantity { get; set; }
        public virtual ICollection<BloodLevelRule> BloodLevelRules { get; set; }
        public virtual BloodType BloodType { get; set; }
        public virtual ICollection<Institution> Institutions { get; set; }
    }
}
