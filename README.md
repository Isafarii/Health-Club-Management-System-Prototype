# SoftEng Health Club Management System

## Overview

SoftEng Health Club Management System is a software prototype designed to streamline the membership management process for a health or exercise club called SoftEng. This system allows efficient tracking of membership details, member attendance, renewal notifications, and generation of various reports for the club manager.

## Contributors

- Scrib Goode
- Daniel Gaevskiy
- Iman Safari
- Rue Yin Hu
- Ryan Walentowicz
- Zarak Tariq

## Functional Needs Statement

SoftEng Health Club operates with a membership model where users pay an upfront fee for a specified duration, granting them unlimited access during that period. Memberships range from 6 months to 3 years, with varying prorated fees.

Key Features:
- Unique ID number generated upon registration.
- QRCodeID for quick member identification.
- Recording and notification of member visits.
- Membership renewal on the spot.
- Monthly renewal notices.
- Manager reports for inactive members and other analytics.
- Email notifications for membership expiration and promotions.

## Code Outline

### Project Structure

The project contains all functions inside the `com.example` package, containing the following classes:

- **Database:** Manages the storage and retrieval of member information.
- **DataFilters:** Implements filters for member data based on various criteria.
- **FreqSorter:** Sorts members by visit frequency, identifying the most frequent users.
- **Member:** Represents a club member with attributes such as name, age, email, etc.
- **MemberSignUp:** Handles the registration and sign-up process for new members.
- **TestingFile:** Contains code for testing various functionalities.

### Prototype Status

This is a prototype, and will need additional refinements, information, and organization.

## Usage

1. Clone the repository:
   ```bash
   git clone https://github.com/Isafarii/Health-Club-Management-System-Prototype.git
2. Open the project in your preferred IDE.

3. Customize and extend the code as needed based on project requirements.
## Contributing

Feel free to contribute to the development of this project by forking the repository and submitting pull requests.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
