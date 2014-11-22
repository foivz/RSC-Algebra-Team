using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;

namespace iDonor.Entities.Models.Mapping
{
    public class DonationTypeMap : EntityTypeConfiguration<DonationType>
    {
        public DonationTypeMap()
        {
            // Primary Key
            this.HasKey(t => t.ID);

            // Properties
        }
    }
}
