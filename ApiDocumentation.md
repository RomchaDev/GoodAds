Two main users of the project are advertisers (people who want to advertise get money)
and advertisable (people who create ads and want their ads to be advertised by advertisers)

Models:

User {
    id: String,
    nickName: String,
    name: String,
    bio: String,
    avatarUrl: String,
    posts: String,
    followers: String,
    following: String,
    postPrice: String,
    storyPrice: String
}

Ad {
    id: String,
    title: String,
    description: String,
    type: AdType,
    imageUrls: String[],
    userId: String,
    price: String,
    freePlaces: Int
}

Distribution {
    userId: Int
    oneAdPrice: Int,
    advertisersAmount: Int
}

AdUser {
     id: String // nullable
     ad: Ad,
     user: User
}

LoginResponse {
    token: String,
    user: User
}

LoginRequest {
    val login: String,
    val password: String
}

AdRequest {
    advertiserId: String, // id of the user that is going to advertise the ad
    adId: String // id of the ad that is going to be advertised
}

ChangePricesRequest {
    postPrice: Int,
    storyPrice: Int
}

Users {
    users: User[]
}

Ads {
    ads: Ad[]
}


Tables:

TokenUsers // 1 row = 1 LoginResponse

Ads // 1 row = 1 Ad

UserRequests // 1 row = {
    [id //id of request]
    [User.id //User that requests]
    [Ad.id // Id of the ad that is going to be advertised. The owner of ad can be got by Ad.userId]
}

AdRequests // 1 row = {
    [id // id of request]
    [Ad.id // Id of the ad that is going to be advertised]
    [Advertiser.id //User that is going to advertise]
}

Difference between UserRequests and AdRequests:
UserRequests is used for advertisable could see who wants to advertise their ad
AdRequest is used for advertisers could see the ads that advertisable want them to advertise