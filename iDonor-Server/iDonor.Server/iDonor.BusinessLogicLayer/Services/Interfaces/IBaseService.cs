using iDonor.DataAccessLayer.UnitOfWork;
using System;

namespace iDonor.BusinessLogicLayer.Services.Interfaces
{
    public interface IBaseService : IDisposable
    {
        IUnitOfWork Uow { get; set; }
    }
}
