using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using iDonor.Entities.Models.Base;

namespace iDonor.Entities.Models
{
    [Table("PushNotification")]
    public partial class PushNotification : IBaseEntity
    {
 
        [Column("ID")] 
        public int ID { get; set; }
 
        [Column("MobileDeviceID")] 
        public int MobileDeviceID { get; set; }
 
        [Column("Message")] 
        public string Message { get; set; }
 
        [Column("Status")] 
        public int Status { get; set; }
 
        [Column("CreatedAt")] 
        public System.DateTime CreatedAt { get; set; }
 
        [Column("ModifiedAt")] 
        public System.DateTime ModifiedAt { get; set; }
 
        [Column("Description")] 
        public string Description { get; set; }
        public virtual MobileDevice MobileDevice { get; set; }
    }
}
