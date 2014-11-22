using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using iDonor.Entities.Models.Base;

namespace iDonor.Entities.Models
{
    [Table("MobileDevice")]
    public partial class MobileDevice : IBaseEntity
    {
        public MobileDevice()
        {
            this.PushNotifications = new List<PushNotification>();
        }

 
        [Column("ID")] 
        public int ID { get; set; }
 
        [Column("UserID")] 
        public int UserID { get; set; }
 
        [Column("SmartphonePlatform")] 
        public string SmartphonePlatform { get; set; }
 
        [Column("RegistrationID")] 
        public string RegistrationID { get; set; }
 
        [Column("Active")] 
        public bool Active { get; set; }
 
        [Column("ModifiedAt")] 
        public Nullable<System.DateTime> ModifiedAt { get; set; }
 
        [Column("CreatedAt")] 
        public Nullable<System.DateTime> CreatedAt { get; set; }
 
        [Column("DeviceID")] 
        public string DeviceID { get; set; }
        public virtual User User { get; set; }
        public virtual ICollection<PushNotification> PushNotifications { get; set; }
    }
}
