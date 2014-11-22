using System.Collections.Generic;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;

namespace iDonor.Entities
{
    public interface IDbContext
    {
        bool ProxyCreation { get; set; }

        bool AutoTrackChanges { get; set; }

        bool LazyLoading { get; set; }

        IDbSet<T> Set<T>() where T : class;
        int SaveChanges();

        DbEntityEntry Entry(object o);

        void Dispose();

        IEnumerable<T> SqlQuery<T>(string sql, params object[] parameters);
    }
}
