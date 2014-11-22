using System;
using System.Collections.Specialized;
using System.Configuration;
using System.IO;
using System.Net;
using System.Net.Http;
using System.Net.Mail;
using System.Web;

namespace iDonor.Utility
{
    public static class ErrorLogger
    {
        public static void Log(Exception exc, HttpRequestBase request = null, int iD = -1, String userName = "", bool sendMail = true)
        {

            StreamWriter objSw = null;
            String requestMsg = "";
            String iDStr = "";

            if (iD > -1)
                iDStr = iD.ToString();

            //Extracting from request.Headers into string
            if (request != null)
            {
                int loop1, loop2;
                NameValueCollection coll;

                // Load Header collection into NameValueCollection object.
                coll = request.Headers;

                // Put the names of all keys into a string array.
                String[] arr1 = coll.AllKeys;
                for (loop1 = 0; loop1 < arr1.Length; loop1++)
                {
                    requestMsg += arr1[loop1] + ": ";
                    // Get all values under this key.
                    String[] arr2 = coll.GetValues(arr1[loop1]);
                    for (loop2 = 0; loop2 < arr2.Length; loop2++)
                    {
                        requestMsg += arr2[loop2] + Environment.NewLine;
                    }
                }
                //attaching URL
                requestMsg += "URL: " + request.Url.AbsoluteUri + Environment.NewLine;
            }

            String message = GetExceptionMessage(exc, false, "", requestMsg, iDStr, userName);

            string sFolderName = @"C:\iDonor Logs\WebService Log\";

            if (!Directory.Exists(sFolderName))
                Directory.CreateDirectory(sFolderName);

            string sFilePath = sFolderName + "Error.log";

            objSw = new StreamWriter(sFilePath, true);
            objSw.WriteLine(message + Environment.NewLine);

            if (objSw != null)
            {
                objSw.Flush();
                objSw.Dispose();
            }
            if (sendMail)
                SendErrMail(message);
        }


        public static void Log(Exception exc, HttpRequestMessage request)
        {
            StreamWriter objSw = null;
            String requestMsg = request.Headers.ToString();

            requestMsg += "URL: " + request.RequestUri.AbsolutePath + Environment.NewLine;

            String message = GetExceptionMessage(exc, false, "", requestMsg);

            string sFolderName = @"C:\iDonor Logs\WebService Log\";

            if (!Directory.Exists(sFolderName))
                Directory.CreateDirectory(sFolderName);

            string sFilePath = sFolderName + "Error.log";

            objSw = new StreamWriter(sFilePath, true);
            objSw.WriteLine(message + Environment.NewLine);

            if (objSw != null)
            {
                objSw.Flush();
                objSw.Dispose();
            }

            SendErrMail(message);
        }
        //PushSharp Log
        public static object _locker = new Object();
        public static void Log(Exception _ex, bool isInnerException = false)
        {
            // do it thread safe, not in a hurry, let the others sleep!
            lock (_locker)
            {
                var message = FormatEx(_ex);
                try
                {
                    using (StreamWriter sw = File.AppendText("C:\\Logs\\PushSharp-Errors.log"))
                    {
                        string err = "";

                        if (isInnerException)
                            err += "[INNER EXCEPTION]";
                        else
                            err += Environment.NewLine + Environment.NewLine + "[EXCEPTION]";

                        err += "[" + DateTime.Now.ToString() + "]" + Environment.NewLine + "[" + message + "]";

                        sw.WriteLine(err);
                        sw.Flush();
                    }

                    if (_ex.InnerException != null)
                        Log(_ex.InnerException, true);
                }
                catch (Exception ex)
                {
                    //the uncatchable :(
                }
            }
        }

        public static void GlobalLog(Exception exc, HttpRequest request, int userId = -1, String userName = "")
        {
            StreamWriter objSw = null;
            String strId = "";
            String requestMsg = "";

            foreach (String key in request.Headers.AllKeys)
            {
                requestMsg += (string.Format("{0}: {1}" + Environment.NewLine, key, request.Headers[key]));
            }

            if (userId != -1)
                strId = userId.ToString();

            requestMsg += "URL: " + request.Url.AbsoluteUri + Environment.NewLine;

            String message = GetExceptionMessage(exc, false, "", requestMsg, strId, userName);

            string sFolderName;

            if (userId == -1)
                sFolderName = @"C:\iDonor Logs\WebService Log\";
            else
                sFolderName = @"C:\iDonor Logs\Web Log\";

            if (!Directory.Exists(sFolderName))
                Directory.CreateDirectory(sFolderName);

            string sFilePath = sFolderName + "Error.log";

            objSw = new StreamWriter(sFilePath, true);
            objSw.WriteLine(message + Environment.NewLine);

            if (objSw != null)
            {
                objSw.Flush();
                objSw.Dispose();
            }
            SendErrMail(message);
        }

        private static string FormatEx(Exception ex)
        {
            return "ERROR: " + ex.Message + Environment.NewLine + "STACKTRACE: " + ex.StackTrace;
        }

        public static string GetExceptionMessage(Exception ex, bool isInnerException = false, string err = "", String requestMsg = "", String iD = "", String userName = "")
        {
            String lines = "----------------------------------------------------------------------------------";
            var message = FormatException(ex);
            err += isInnerException ? "[INNER EXCEPTION]" : "[EXCEPTION]";
            if (requestMsg != "" && iD != "")
                err += String.Format("[{0}]" + Environment.NewLine +
                    "USER(ID): {1}({2})" + Environment.NewLine +
                    "{3}" + Environment.NewLine +
                    "{4}" +
                    "{5}" + Environment.NewLine +
                    "HTTP REQUEST:" + Environment.NewLine +
                    "{6}" +
                    "{7}" + Environment.NewLine +
                    Environment.NewLine + Environment.NewLine,
                    DateTime.Now.ToString(), userName, iD, lines, message, lines, requestMsg, lines);
            else if (requestMsg != "")
            {
                err += String.Format("[{0}]" + Environment.NewLine +
                    "{1}" + Environment.NewLine +
                    "{2}" +
                    "{3}" + Environment.NewLine +
                    "HTTP REQUEST:" + Environment.NewLine +
                    "{4}" +
                    "{5}" + Environment.NewLine +
                    Environment.NewLine + Environment.NewLine,
                    DateTime.Now.ToString(), lines, message, lines, requestMsg, lines);
            }
            else
            {
                err += String.Format("[{0}]" + Environment.NewLine +
                    "{1}" + Environment.NewLine +
                    "{2}" +
                    "{3}" + Environment.NewLine +
                    Environment.NewLine + Environment.NewLine,
                    DateTime.Now.ToString(), lines, message, lines);
            }

            if (ex.InnerException != null)
                return GetExceptionMessage(ex.InnerException, true, err, requestMsg, iD, userName);
            else
                return err;
        }

        private static string FormatException(Exception ex)
        {
            return "MESSAGE: " + ex.Message + Environment.NewLine + "STACKTRACE: " + ex.StackTrace + Environment.NewLine;
        }

        public static void SendErrMail(string errMsg)
        {
            var settings = ConfigurationManager.AppSettings;

            string to = settings["adminEmail"];
            string from = settings["iDonorErrorLog"];
            MailMessage message = new MailMessage(from, to);
            message.Subject = "iDonor ErrorLog";
            message.Body = errMsg;

            SmtpClient client = new SmtpClient(settings["smtpServer"]);
            client.Credentials = new NetworkCredential(settings["smtpUser"], settings["smtpPass"]);
            client.Port = Convert.ToInt32(settings["smtpPort"]);
            client.EnableSsl = Convert.ToBoolean(settings["EnableSsl"]);
            client.Timeout = 5 * 1000;

            try
            {
                client.Send(message);
            }
            catch (Exception ex)
            {
                Log(ex, sendMail: false);
            }
        }
    }
}
