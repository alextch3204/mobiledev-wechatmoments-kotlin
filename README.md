### Requirements
> :warning: **If you are using Android Studio Artic Fox 2020.3.1**, please clone the code at [as_articfox branch](https://github.com/techops-recsys-lateral-hiring/mobiledev-wechatmoments-kotlin/tree/as_articfox)
1. Android Studio 4 (4.2.2) - via [Android Developers Archive](https://developer.android.com/studio/archive)
2. Android SDK 30 and BuildTools 29.0.2 - via [Android SDK Manager](https://developer.android.com/studio/intro/update#sdk-manager)
3. Java JDK 8 - via [Termurin OpenJDK](https://adoptium.net/?variant=openjdk8&jvmVariant=hotspot) or [Oracle Java Downloads](https://www.oracle.com/java/technologies/javase/javase8u211-later-archive-downloads.html)
4. npm - via [Node.js installer](https://nodejs.org/en/download/) or [nvm](https://github.com/nvm-sh/nvm#install--update-script)

### Setup the project
1. Locate the current directory in terminal
3. Execute `npm install -g mountebank`
4. Execute `mb --configfile imposters.ejs`
5. Open `build.gradle` in the current directory
6. Run the project and make sure the app can start correctly(don't worry about if the features are working properly)

### App Introduction

The code is an Android app which looks like Wechat Moments page. 

We have some requirements during building this app, and you should also try to follow these requirements:

#### Project overview

The below screenshots are from Wechat App, they are for reference/inspiration these do not represent the actual output from the given codebase.

<img src="https://user-images.githubusercontent.com/61306682/131655545-cfa011b4-637f-45db-bb26-3bb9c986b94b.png" alt="wechat_moments_2" height=350 /> <img src="https://user-images.githubusercontent.com/61306682/131655537-43e4ab0b-29f0-456d-bf2a-0fcf3de0ba2c.jpg" alt="wechat_moments_3" height=350 /> <img src="https://user-images.githubusercontent.com/61306682/131655555-608f9b7e-5cb7-4059-abbc-f70dfd00fe06.jpg" alt="wechat_moments_1" height=350 />

- The project is an Android app which looks like Wechat Moments page.
- The page consists of profile image, avatar and tweets list
- For each tweet, there will be a sender, optional content, optional images and comments
- A tweet contains from 0 to 9 images
- Pulling down table view to refresh, only first 5 items are shown after refreshing
- All tweets are fetched and stored in memory at the first time and only show first 5 of them at the beginning and after refresh.
- Show 5 more while user pulling up the view at the bottom of the table view.
- Supports layout on all kinds of android device screen and orientation.
- This is a static page.

#### Tech requirements:

- The data JSON will be hosted at localhost:2727
- An example of the response in `Tweet.json`
- Unit tests are appreciated.
- Functional programming is appreciated
- Keep your code clean as much as possible Production and Technical requirements are weighing equally in the final result.
