using iDonor.BusinessLogicLayer.Services.Base;
using iDonor.DataAccessLayer.UnitOfWork;
using iDonor.Utility;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace iDonor.BusinessLogicLayer.Services.Implementations.UserService
{
    public class UserService : BaseService<UserServiceWeb, UserServiceMobile>
    {

        private void Constructor(ServiceType serviceType)
        {
            switch (serviceType)
            {
                case ServiceType.Base:
                    break;
                case ServiceType.All:
                    this.Web = new UserServiceWeb(this);
                    this.Mobile = new UserServiceMobile(this);
                    break;
                case ServiceType.Web:
                    this.Web = new UserServiceWeb(this);
                    break;
                case ServiceType.Mobile:
                    this.Mobile = new UserServiceMobile(this);
                    break;
            }
        }

        public UserService(ServiceType serviceType = ServiceType.All)
        {
            Constructor(serviceType);
        }

        public UserService(ServiceType serviceType = ServiceType.All, IUnitOfWork uow = null)
            : base(uow)
        {
            Constructor(serviceType);
        }
    }
}
