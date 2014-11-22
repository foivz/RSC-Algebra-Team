using iDonor.DataAccessLayer.Repository;
using iDonor.Entities;
using iDonor.Entities.Models;
using iDonor.Entities.Models.Base;
using iDonor.Utility;
using System;
using System.Collections;
using System.Data.Entity;

namespace iDonor.DataAccessLayer.UnitOfWork
{
    public class UnitOfWork : IUnitOfWork
    {
        private readonly IDbContext context;

        public IDbContext Context
        {
            get { return context; }
        }

        private Hashtable _repositories;
        private bool disposed = false;

        public UnitOfWork(Type ctxType = null, IDbContext ctx = null, bool lazyLoading = true)
        {
            ctxType = ctxType ?? typeof(iDonorContext);

            context = ctx ?? (IDbContext)Activator.CreateInstance(ctxType);

            if (context.GetType().BaseType == typeof(DbContext))
            {
                var c = ((DbContext)context);
                c.Configuration.LazyLoadingEnabled = lazyLoading;
                c.Configuration.ValidateOnSaveEnabled = false;
            }
        }

        public bool Save()
        {
            try
            {
                context.SaveChanges();
                return true;
            }
            catch (Exception ex)
            {
                //TODO: mleskovar
                //ErrorLogger.Log(ex, false);
                return false;
            }
        }

        public void Dispose(bool disposing)
        {
            if (!this.disposed)
            {
                if (disposing)
                {
                    context.Dispose();
                }
            }
            this.disposed = true;
        }

        public void Dispose()
        {
            Dispose(true);
            GC.SuppressFinalize(this);
        }

        /// <summary>
        /// Repository Factory method. Stores IRepository<T> instances in an internal hashtable, so only once one targeted instance can be instantiated.
        /// </summary>
        /// <typeparam name="T">Entity Type</typeparam>
        /// <param name="shareContext">Shares the database context by default, if set to false, changes are persisted immediatelly and not over the UoW SaveChanges().</param>
        /// <returns>An IRepository<T> interface for a targeted Entity/Table.</returns>
        public IGenericRepository<T> GetRepositoryFactory<T>(bool shareContext = true) where T : class, IBaseEntity
        {
            if (_repositories == null)
                _repositories = new Hashtable();

            var type = typeof(T).Name;
            if (!_repositories.ContainsKey(type))
            {
                var repositoryType = typeof(GenericRepository<>);

                var repositoryInstance =
                        Activator.CreateInstance(repositoryType
                               .MakeGenericType(typeof(T)), context, shareContext);

                _repositories.Add(type, repositoryInstance);
            }

            return (IGenericRepository<T>)_repositories[type];
        }
    }
}
