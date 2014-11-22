using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;

namespace iDonor.Entities.Models.Mapping
{
    public class PushNotificationMap : EntityTypeConfiguration<PushNotification>
    {
        public PushNotificationMap()
        {
            // Primary Key
            this.HasKey(t => new { t.ID, t.MobileDeviceID, t.Status, t.CreatedAt, t.ModifiedAt });

            // Properties
            this.Property(t => t.ID)
                .HasDatabaseGeneratedOption(DatabaseGeneratedOption.Identity);

            this.Property(t => t.MobileDeviceID)
                .HasDatabaseGeneratedOption(DatabaseGeneratedOption.None);

            this.Property(t => t.Status)
                .HasDatabaseGeneratedOption(DatabaseGeneratedOption.None);


            // Relationships
            this.HasRequired(t => t.MobileDevice)
                .WithMany(t => t.PushNotifications)
                .HasForeignKey(d => d.MobileDeviceID);

        }
    }
}
