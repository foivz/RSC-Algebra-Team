using iDonor.DataAccessLayer.UnitOfWork;
using iDonor.Utility;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace iDonor.BusinessLogicLayer.Services
{
    public class ServiceManager
    {
        private IUnitOfWork uow;

        internal IUnitOfWork Uow
        {
            get
            {
                if (this.uow == null)
                    this.uow = new UnitOfWork();

                return this.uow;
            }
        }

        public ServiceManager()
        {

        }

        private Hashtable _services;

        public T Get<T>(ServiceType accessType = ServiceType.All) where T : class
        {
            if (this._services == null)
                this._services = new Hashtable();

            var type = typeof(T).Name;
            if (!this._services.ContainsKey(type))
            {
                var serviceType = typeof(T);
                var serviceInstance = Activator.CreateInstance(serviceType, accessType, Uow);

                this._services.Add(type, serviceInstance);
            }

            return (T)this._services[type];
        }
    }
}
