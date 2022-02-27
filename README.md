![](https://i.imgur.com/tHwZdAy.png)

## Inspiration
For VTHacks, we created a mobile app called **StudentAngle**, which provides course guidance for students by their peers, along with factual course information retrieved from verified resources. We were inspired to build this project because most information that students look for when registering for classes is scattered across multiple platforms like Discord, Reddit, Facebook Groups, GroupMe, etc, which can be hard to keep track of. This motivated us to create an application that aggregates this information in an intuitive way in order to best understand how a class is truly taught and if it is the right fit for them.  

## What it does
StudentAngle aggregates information on 6000+ classes in 129 subject areas within Virginia Tech into user-friendly info cards. Each card contains basic information such as the course subject and number, class name, professor, tags showing the course modality and semester(s) offered, and a brief description. The student perspective is added in the form of custom tags and editable descriptions. In addition, users can favorite and set aside certain cards for future reference.

![](https://i.imgur.com/dQpYlb6.png)

 
## How we built it
We primarily built the application using Kotlin and Android Studio. First, we designed wireframes in Figma. Then, using the native Android functionality, we created the XML layout of the app. 

We parsed information from the [Timetable of Classes](https://apps.es.vt.edu/ssb/HZSKVTSC.P_ProcRequest) from the official Virginia Tech website to get information including: the course subject and number, course name, course professor, the course modality and semester(s) offered, and a brief description. We did this using the  [Beautiful Soup](https://www.crummy.com/software/BeautifulSoup/bs4/doc/) library in Python.

![](https://i.imgur.com/qrEWEZ6.png)


We also built a functionality where all favorited classes show up as a separate list that the user can refer back to. And, to incorporate the student perspective, we had tags specific to each class.

## Challenges we ran into
» integrating web scraped data with our app <br/>
» learning Android application architecture and utilizing Android Studio features <br/>
» predicting the amount of time implementing a feature will take <br/>
» needing to prioritize features from our original idea due to time limitations 

## Accomplishments that we're proud of
We were able to systematically delegate duties and create a functional app within 24 hours. If one of us was struggling, we all worked to alleviate their burden and help each other out. 

Our app also presents users with a splash screen on startup, one unique feature that has made our app look more professional. It showcases our logo, whose design contains an S appended to an A for StudentAngle, where the arrow signifies an upward trend in a student’s knowledge of offered courses.

## What we learned
We learned effective teamwork and how to build an app with various functionalities in Android Studio! We also walked through the Android Studio User Guides by Google. We utilized GitHub as our source control software for this project, which involved coordination and communication between our team members to maintain error-free source code. 

## What's next for StudentAngle
We would like to implement a discussion thread functionality, where students can ask further questions. This was something we intended to implement but due to lack of time and limited resources describing how to build a forum on an app, we decided to forego it for the future. In addition, we would like to implement login functionality and a proper backend, so the user can access this data at a later time. We predict that the upvoting/downvoting feature card would be an extremely useful feature, and hope to implement it in our app. In addition, we want to add scheduling functionality, so students can choose cards in a schedule format and easily come up with a plan of study for a better college experience.

![](https://i.imgur.com/5yOUpCp.png)


## Developers
[Chau Le](https://www.linkedin.com/in/chau-le-80b713192/), 
[Ria Vadhavkar](https://www.linkedin.com/in/riavadhavkar/), 
[Dana Altarace](https://www.linkedin.com/in/dana-altarace-20b498196/), 
[Aditi Diwan](https://www.linkedin.com/in/aditi-diwan/)
