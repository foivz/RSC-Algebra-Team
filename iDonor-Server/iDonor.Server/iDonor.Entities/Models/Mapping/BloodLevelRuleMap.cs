using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;

namespace iDonor.Entities.Models.Mapping
{
    public class BloodLevelRuleMap : EntityTypeConfiguration<BloodLevelRule>
    {
        public BloodLevelRuleMap()
        {
            // Primary Key
            this.HasKey(t => t.ID);

            // Properties

            // Relationships
            this.HasRequired(t => t.BloodStorage)
                .WithMany(t => t.BloodLevelRules)
                .HasForeignKey(d => d.BloodStorageID);

        }
    }
}
