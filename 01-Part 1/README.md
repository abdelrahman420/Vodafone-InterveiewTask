# STLC for LinkedIn Mobile Application

## 1. Test Ideas

- **User Registration & Login:** 
  - Test creating an account.
  - Test logging in with email and password.
  - Test for password recovery.

- **Messaging:** 
  - Test sending messages and receiving.
  - Test editing deleting messages.
  - Test message search and filtering.

- **Networking:**
  - Test sending and receiving connection requests.
  - Test searching and filtering users by industry, location, job title, etc.
  - Test viewing, liking, commenting on, and sharing posts.
  - Test managing invitations and follow requests.

- **Notifications:** 
  - Test real-time notifications for new connections, messages, job alerts, profile views, and endorsements.

- **Job Search and Application:**
  - Test searching for jobs with different filters (location, industry, experience).
  - Test applying for jobs directly with or without a LinkedIn profile.
  - Test saving jobs and setting job alerts.
  - Test job recommendations based on profile data.

- **Navigation:**
  - Test the ease of navigating between different sections (Home, Profile, Jobs, Messaging).
  - Test the responsiveness and load times of different pages.

- **Stress Testing:**
  - Test app performance under extreme conditions (e.g., low battery, low memory, poor network conditions).
  
- **Network Compatibility:**
  - Test the app on different network types (Wi-Fi, 4G, 5G).
  - Test the app’s behavior in offline mode and under poor network conditions.
  - Test the app's ability to sync data when transitioning between network types.

## 2. Prioritization of Test Cases

### High Priority (Critical Business Impact)
1. **User Registration & Login:** This is the gateway for user acquisition and access to the platform.
2. **Job Search and Application:** Core to LinkedIn's value proposition, especially for job seekers and recruiters.
3. **Messaging:** Messaging is crucial for user interaction and networking on LinkedIn.

### Medium Priority (Moderate Business Impact)
1. **Networking:** Vital for user engagement and growth, but may not have as immediate an impact as login or job search functionalities.
2. **Navigation:** A smooth and intuitive navigation experience is crucial for user satisfaction. Poor navigation can frustrate users.
3. **Notifications:** Keeps users informed and engaged with the platform.

### Low Priority (Minor Business Impact)
1. **Stress Testing:** Ensures app stability under extreme conditions but typically affects only a small percentage of users.
2. **Network Compatibility:** Important for ensuring a smooth user experience across different network conditions.

## 3. Bug Reports

**Note:** Assumed bugs for completion of the task.

### Bug 1: Login Button Unresponsive

- **Title:** Login Button Unresponsive
- **Reproduce Steps:**
  1. Open the LinkedIn app.
  2. Enter a valid email and password.
  3. Tap on the "Login" button.
  4. Observe the response.
- **Attachments:** Screenshot showing the login screen with the unresponsive button.
- **Severity:** High
- **Priority:** High

### Bug 2: Messages Not Displaying Correctly After Sending

- **Title:** Sent Messages Not Displaying Correctly
- **Reproduce Steps:**
  1. Open the LinkedIn app.
  2. Navigate to the messaging section.
  3. Send a message to a connection.
  4. Observe the message display.
- **Attachments:** Screenshot of the conversation where the message does not appear.
- **Severity:** Medium
- **Priority:** High

### Bug 3: Job Alerts Not Triggering Notification

- **Title:** Job Alerts Notification Not Working
- **Reproduce Steps:**
  1. Set up job alerts in the LinkedIn app.
  2. Wait for a relevant job posting.
  3. Observe if a notification is received.
- **Attachments:** Log showing job alert settings and absence of notification.
- **Severity:** Medium
- **Priority:** Medium

### Bug 4: App Crashes on Profile Editing

- **Title:** App Crashes When Editing Profile
- **Reproduce Steps:**
  1. Open the LinkedIn app.
  2. Go to the Profile section.
  3. Attempt to edit any profile detail (e.g., work experience).
  4. Observe the app's behavior.
- **Attachments:** Crash log or video showing the crash during profile editing.
- **Severity:** High
- **Priority:** High

### Bug 5: Slow Performance in Job Search Filters

- **Title:** Slow Performance When Applying Job Search Filters
- **Reproduce Steps:**
  1. Open the LinkedIn app.
  2. Go to the Jobs section.
  3. Apply multiple filters (e.g., location, industry).
  4. Observe the loading time and app performance.
- **Attachments:** Video demonstrating the slow performance when applying filters.
- **Severity:** Low
- **Priority:** Medium

### Bug 6: Inconsistent Notifications for Connection Requests

- **Title:** Connection Requests Not Triggering Notifications
- **Reproduce Steps:**
  1. Receive a connection request on the LinkedIn app.
  2. Observe if a notification is received.
- **Attachments:** Screenshot showing the connection request in the app without a corresponding notification.
- **Severity:** Medium
- **Priority:** Medium
