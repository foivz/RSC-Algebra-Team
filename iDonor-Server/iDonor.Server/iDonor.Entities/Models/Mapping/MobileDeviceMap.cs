using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;

namespace iDonor.Entities.Models.Mapping
{
    public class MobileDeviceMap : EntityTypeConfiguration<MobileDevice>
    {
        public MobileDeviceMap()
        {
            // Primary Key
            this.HasKey(t => t.ID);

            // Properties

            // Relationships
            this.HasRequired(t => t.User)
                .WithMany(t => t.MobileDevices)
                .HasForeignKey(d => d.UserID);

        }
    }
}
