using iDonor.BusinessLogicLayer.Services.Interfaces;
using iDonor.DataAccessLayer.UnitOfWork;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace iDonor.BusinessLogicLayer.Services.Base
{
    public abstract class BaseService
    {
        private bool _disposed = false;
        private IUnitOfWork _uow;

        public IUnitOfWork Uow
        {
            get { return _uow; }
            set { _uow = value; }
        }
        //protected IRepository<TEntity> serviceRepo;

        internal BaseService(IUnitOfWork _uow = null)
        {
            this._uow = _uow ?? new UnitOfWork();
            //uzrokuje thight bond između BaseApiControllera i Entites liba, što ne želimo ni pod razno... želimo imati nezavisne module
            //this.serviceRepo = this.uow.GetRepositoryFactory<TEntity>();
        }

        #region IDisposable Members

        public void Dispose(bool disposing)
        {
            if (!this._disposed)
            {
                if (disposing)
                    this._uow.Dispose();
            }
            this._disposed = true;
        }

        public void Dispose()
        {
            Dispose(true);
            GC.SuppressFinalize(this);
        }

        #endregion
    }

    /// <summary>
    /// A generic overload for the BaseService class used to abstract the Web and Mobile internal service methods. A real nifty hack.
    /// </summary>
    public abstract class BaseService<TWebService, TMobileService> : BaseService, IDisposable, IBaseService
    {
        public TWebService Web { get; set; }
        public TMobileService Mobile { get; set; }

        public BaseService()
        {

        }

        public BaseService(IUnitOfWork uow)
            : base(uow)
        {

        }
    }

    public abstract class ParentService<TService> where TService : class
    {
        private TService _base;

        public TService Parent
        {
            get { return _base; }
            set { _base = value; }
        }

        public ParentService(TService service)
        {
            this.Parent = service;
        }
    }
}
