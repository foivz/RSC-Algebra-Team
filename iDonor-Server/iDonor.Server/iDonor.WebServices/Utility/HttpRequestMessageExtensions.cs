using System;
using System.Net.Http;

namespace iDonor.WebServices.Utility
{
    /// <summary>
    /// Helper class with extensions over the HttpRequestMessage class
    /// </summary>
    public static class HttpRequestMessageExtensions
    {
        private const string HttpContext = "MS_HttpContext";
        private const string RemoteEndpointMessage = "System.ServiceModel.Channels.RemoteEndpointMessageProperty";


        /// <summary>
        /// Exposes the client IP address from the request object.
        /// </summary>
        /// <param name="request">A HttpRequestMessage.</param>
        /// <returns>The IP address of the request sender.</returns>
        public static string GetClientIpAddress(this HttpRequestMessage request)
        {
            if (request.Properties.ContainsKey(HttpContext))
            {
                dynamic ctx = request.Properties[HttpContext];
                if (ctx != null)
                {
                    return ctx.Request.UserHostAddress;
                }
            }

            if (request.Properties.ContainsKey(RemoteEndpointMessage))
            {
                dynamic remoteEndpoint = request.Properties[RemoteEndpointMessage];
                if (remoteEndpoint != null)
                {
                    return remoteEndpoint.Address;
                }
            }

            return null;
        }
    }
}