Routes list
===========

ROUTE AND PARAMETERS										DESCRIPTION 							                                           
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
- USER
-----------
#GET 
/api/user/login?username=(string)&password=(string)
->{LoginAppUserResponseModel}

#POST
/api/user/register?{RegisterAppUserRequestModel}
->{RegisterAppUserResponseModel}

- INSTITUTION
-----------
#GET
/api/institution/
->

#POST

- STATISTICS
-----------
#GET
/api/statistics/getForUserId?userId=(int)
->{StatisticsForUserResponseModel}

#POST


- PUSH NOTIFICATIONS
-----------
#GET
/api/pushNotifications/registerDevice?userId=(int)&registrationId=(string)&platform=(string:{ios|android})&deviceId=(string)
->{SaveResponseModel}

/api/pushNotifications/setStateForUser?active=(int:{1|0})
->{SaveResponseModel}

#POST


REQUEST JSON MODELS
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
RegisterAppUserRequestModel
request:
{
	username:(string),
	password:(string),
	email:(string),
	firstName:(string),
	lastName:(string),
	age:(int),
	weight(decimal),
	bloodTypeId:(int),
	town:(string?) //poslije može townID,
	address:(string?),
	phoneNumber:(string?),
	postalNumber:(string?)
}


RESPONSE JSON MODELS
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
#BASE RESPONSE
response:
{
	...
},
message: (string),
status: (100-500)

{SaveResponseModel}
response:
{
	saved:(bool)
}
message: (string),
status: (100-500)

{RegisterAppUserResponseModel}
response:
{
	registrationStatus: (100-500)
},
message: (string),
status: (100-500)

{LoginAppUserResponseModel}
response:
{
	userId:(int),
	loginStatus:(100-500),
	email:(string),
	firstName:(string),
	lastName:(string),
	age:(int),
	weight(decimal),
	bloodTypeId:(int),
	town:(string?) //poslije može townID,
	address:(string?),
	phoneNumber:(string?),
	postalNumber:(string?),
	timeSinceLastDonation:(int), //seconds
	bloodDonations: [ // sorted by date - ivan
		{ 
			bloodDonationId:(int),
			date:(string),
			institutionID:(int),
			institutionName:(string)
		} ...]
},
message: (string),
status: (100-500)

{StatisticsForUserResponseModel}
response:
{
	...
},
message: (string),
status: (100-500)







- Gets mobile static data (countries, states, sports, positions): 					/api/data/static	
- Gets all training days for a week id [wid]:                                       /api/days/allbyweekid?wid=(int)
