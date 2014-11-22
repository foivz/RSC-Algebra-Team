using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace iDonor.Entities.Models
{
    /// <summary>
    /// Partial extensions to the DataBase context, provides extra flexibility and dependency decoupling
    /// </summary>
    public partial class iDonorContext : DbContext, IDbContext
    {
        /// <summary>
        /// A wrapper over the DbSet to get a generic access over an Entity Class Type.
        /// </summary>
        /// <typeparam name="T">Entity Class name.</typeparam>
        /// <returns>An IDbSet of the requested entity type.</returns>
        public new IDbSet<T> Set<T>() where T : class
        {
            return base.Set<T>();
        }

        public bool AutoTrackChanges
        {
            get { return this.Configuration.AutoDetectChangesEnabled; }
            set { this.Configuration.AutoDetectChangesEnabled = value; }
        }

        public bool LazyLoading
        {
            get { return this.Configuration.LazyLoadingEnabled; }
            set { this.Configuration.LazyLoadingEnabled = value; }
        }

        public bool ProxyCreation
        {
            get { return this.Configuration.ProxyCreationEnabled; }
            set { this.Configuration.ProxyCreationEnabled = value; }
        }

        /// <summary>
        /// Enables direct delegation of sql query over the DbContext to the database.
        /// </summary>
        /// <typeparam name="T">The object type to map the return result.</typeparam>
        /// <param name="sql">SQL query in a string.</param>
        /// <param name="parameters">SQL query parameters.</param>
        /// <returns>A projected IEnumerable of the selected type.</returns>
        public IEnumerable<T> SqlQuery<T>(string sql, params object[] parameters)
        {
            return Database.SqlQuery<T>(sql, parameters);
        }
    }
}
