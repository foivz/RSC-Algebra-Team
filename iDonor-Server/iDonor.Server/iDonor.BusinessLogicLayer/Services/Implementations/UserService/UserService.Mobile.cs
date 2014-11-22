using iDonor.BusinessLogicLayer.Services.Base;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace iDonor.BusinessLogicLayer.Services.Implementations.UserService
{
    public class UserServiceMobile : ParentService<UserService>
    {
        public UserServiceMobile(UserService service) : base(service) { }

    }
}
