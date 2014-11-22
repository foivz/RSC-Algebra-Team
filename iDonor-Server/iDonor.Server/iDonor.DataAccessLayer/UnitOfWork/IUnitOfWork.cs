using iDonor.DataAccessLayer.Repository;
using iDonor.Entities;
using iDonor.Entities.Models.Base;

namespace iDonor.DataAccessLayer.UnitOfWork
{
    public interface IUnitOfWork
    {
        IDbContext Context { get; }
        void Dispose();
        void Dispose(bool disposing);
        bool Save();
        IGenericRepository<T> GetRepositoryFactory<T>(bool shareContext = true) where T : class, IBaseEntity;
    }
}
