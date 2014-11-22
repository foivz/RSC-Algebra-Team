Routes list
===========

ROUTE AND PARAMETERS										DESCRIPTION 							                                           
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
- USER
-----------
#GET 
/api/user/login?username=(string)&password=(string)
->{LoginAppUserResponseModel}

/api/user/resetPassword?userName=(string)
->{SaveResponseModel} //salje se na mail password

#POST
/api/user/register?{RegisterAppUserRequestModel}
->{RegisterAppUserResponseModel}

- INSTITUTION
-----------
#GET
/api/institution/getInstitutionById?institutionId=(int)
->{InstitutionResponseModel}

#POST

- STATISTICS
-----------
#GET
/api/statistics/getForUserId?userId=(int)
->{StatisticsForUserResponseModel}

/api/statistics/getGlobalStatistics
->{GlobalStatisticsResponseModels}

#POST


- EVENTS
-----------

#GET
/api/user/register?{RegisterAppUserRequestModel}
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
    sex:(string),//mf
	age:(int),
	weight(decimal),
	bloodTypeId:(int),
	city:(string?) //poslije može townID,
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
	bloodDonationCount:(int),
    litresOfDonatedBlood:(int),
    bloodDonations: [ // sorted by date - ivan
		{ 
			year:(string),
            numberOfDonations:(int)
		} ...]

},
message: (string),
status: (100-500)

{GlobalStatisticsResponseModels}
response:
{
	bloodDonationsCount:(int),
    litresOfDonatedBlood:(int),
    donatorCount:(int),
    femaleDonatorsCount:(int),
    maleDonatorsCount:(int),
    numberOfLivesSaved:(int),
    averageAge:(float)
},
message: (string),
status: (100-500)

{InstitutionResponseModel}
response:
{
    name:(string),
    city:(string),
    adress:(int),
    postalNumber(int),
    phoneNumber:(string),
    bloodLevels: [ 
		{ 
			bloodType:(string),
            level:(int)
		} ...]
     
},
message: (string),
status: (100-500)


- Gets mobile static data (countries, states, sports, positions): 					/api/data/static	
- Gets all training days for a week id [wid]:                                       /api/days/allbyweekid?wid=(int)
