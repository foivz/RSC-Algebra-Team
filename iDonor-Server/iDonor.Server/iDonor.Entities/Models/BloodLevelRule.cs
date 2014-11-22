using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using iDonor.Entities.Models.Base;

namespace iDonor.Entities.Models
{
    [Table("BloodLevelRule")]
    public partial class BloodLevelRule : IBaseEntity
    {
 
        [Column("ID")] 
        public int ID { get; set; }
 
        [Column("BloodLevel")] 
        public int BloodLevel { get; set; }
 
        [Column("ToQuantity")] 
        public int ToQuantity { get; set; }
 
        [Column("BloodStorageID")] 
        public int BloodStorageID { get; set; }
        public virtual BloodStorage BloodStorage { get; set; }
    }
}
