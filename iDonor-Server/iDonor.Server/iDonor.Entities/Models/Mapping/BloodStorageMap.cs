using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;

namespace iDonor.Entities.Models.Mapping
{
    public class BloodStorageMap : EntityTypeConfiguration<BloodStorage>
    {
        public BloodStorageMap()
        {
            // Primary Key
            this.HasKey(t => t.ID);

            // Properties

            // Relationships
            this.HasRequired(t => t.BloodType)
                .WithMany(t => t.BloodStorages)
                .HasForeignKey(d => d.BloodTypeID);

        }
    }
}
