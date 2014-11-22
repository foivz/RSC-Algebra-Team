using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;

namespace iDonor.Entities.Models.Mapping
{
    public class InstitutionMap : EntityTypeConfiguration<Institution>
    {
        public InstitutionMap()
        {
            // Primary Key
            this.HasKey(t => t.ID);

            // Properties
            this.Property(t => t.Name)
                .IsRequired();


            // Relationships
            this.HasRequired(t => t.BloodStorage)
                .WithMany(t => t.Institutions)
                .HasForeignKey(d => d.BloodStorageID);

        }
    }
}
