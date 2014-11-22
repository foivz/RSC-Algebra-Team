using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Http;
using System.Web.Routing;
using iDonor.Utility;

namespace iDonor.WebServices
{
    public class WebApiApplication : System.Web.HttpApplication
    {
        protected void Application_Start()
        {
            GlobalConfiguration.Configure(WebApiConfig.Register);
        }
        //protected void Application_Error(object sender, EventArgs e)
        //{
        //    Exception ex = Server.GetLastError();
        //    Server.ClearError();
        //    ErrorLogger.GlobalLog(ex, Request);
        //}
    }
}
