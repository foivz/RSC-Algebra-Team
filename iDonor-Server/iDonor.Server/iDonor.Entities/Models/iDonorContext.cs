using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using iDonor.Entities.Models.Mapping;

namespace iDonor.Entities.Models
{
    public partial class iDonorContext : DbContext
    {
        static iDonorContext()
        {
            Database.SetInitializer<iDonorContext>(null);
        }

        public iDonorContext()
            : base("Name=iDonorContext")
        {
        }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Configurations.Add(new BloodLevelRuleMap());
            modelBuilder.Configurations.Add(new BloodStorageMap());
            modelBuilder.Configurations.Add(new BloodTypeMap());
            modelBuilder.Configurations.Add(new DonationMap());
            modelBuilder.Configurations.Add(new DonationTypeMap());
            modelBuilder.Configurations.Add(new InstitutionMap());
            modelBuilder.Configurations.Add(new MobileDeviceMap());
            modelBuilder.Configurations.Add(new PushNotificationMap());
            modelBuilder.Configurations.Add(new RoleMap());
            modelBuilder.Configurations.Add(new UserMap());
        }
    }
}
