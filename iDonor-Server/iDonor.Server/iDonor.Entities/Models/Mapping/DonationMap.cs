using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;

namespace iDonor.Entities.Models.Mapping
{
    public class DonationMap : EntityTypeConfiguration<Donation>
    {
        public DonationMap()
        {
            // Primary Key
            this.HasKey(t => t.ID);

            // Properties

            // Relationships
            this.HasRequired(t => t.BloodType)
                .WithMany(t => t.Donations)
                .HasForeignKey(d => d.BloodTypeID);
            this.HasRequired(t => t.DonationType)
                .WithMany(t => t.Donations)
                .HasForeignKey(d => d.DonationTypeID);
            this.HasRequired(t => t.Institution)
                .WithMany(t => t.Donations)
                .HasForeignKey(d => d.InstitutionID);
            this.HasOptional(t => t.User)
                .WithMany(t => t.Donations)
                .HasForeignKey(d => d.UserID);

        }
    }
}
