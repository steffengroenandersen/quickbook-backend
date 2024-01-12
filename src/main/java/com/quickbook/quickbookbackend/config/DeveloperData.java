package com.quickbook.quickbookbackend.config;

import com.quickbook.quickbookbackend.dto.RoomRequest;
import com.quickbook.quickbookbackend.entity.Guest;
import com.quickbook.quickbookbackend.entity.Hotel;
import com.quickbook.quickbookbackend.entity.Reservation;
import com.quickbook.quickbookbackend.entity.Room;
import com.quickbook.quickbookbackend.repository.GuestRepository;
import com.quickbook.quickbookbackend.repository.HotelRepository;
import com.quickbook.quickbookbackend.repository.ReservationRepository;
import com.quickbook.quickbookbackend.repository.RoomRepository;
import com.quickbook.security.entity.Role;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Configuration
public class DeveloperData implements ApplicationRunner {

    GuestRepository guestRepository;
    HotelRepository hotelRepository;
    RoomRepository roomRepository;
    ReservationRepository reservationRepository;

    public DeveloperData(GuestRepository guestRepository, HotelRepository hotelRepository, RoomRepository roomRepository, ReservationRepository reservationRepository) {
        this.guestRepository = guestRepository;
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("RUNNING PROGRAM......................");
        System.out.println("INSERTING DEVELOPER DATA......................");

        // Add Admins
        Guest admin1 = new Guest("admin1", "admin1@localhost.com", "pass", "Steffen", "Andersen");
        admin1.addRole(Role.ADMIN);
        guestRepository.save(admin1);

        // Add Guests
        Guest guest1 = new Guest("guest1", "guest1@localhost.com", "pass", "Peter", "Andersen");
        guest1.addRole(Role.USER);
        guestRepository.save(guest1);

        Guest guest2 = new Guest("guest2", "guest2@localhost.com", "pass", "Anders", "Andersen");
        guest2.addRole(Role.USER);
        guestRepository.save(guest2);
        
        // Generate and save hotels and rooms
       // List<Hotel> hotels = generateHotels();
        
       // List<Guest> guestList = Arrays.asList(guest1, guest2);
        
        // Generate and save reservations
        //List<Reservation> reservations = generateReservations(guestList, hotels);
        
    }
    
    public List<Hotel> generateHotels(){
        List<Hotel> hotels = new ArrayList<>();
        
        // Generate hotels
        for(int i = 1; i <= 250; i++){
            String name = dummyHotelNames.get(i);
            String street = dummyStreetNames.get(i);
            String city = dummyCityNames.get(i);
            int zip = generateRandomZipNumber();
            String country = COUNTRY_NAMES.get(i);
            
            Hotel newHotel = new Hotel(name, street, city, zip, country);
            hotels.add(newHotel);
        }

        // Generate rooms for hotels and set them
        for(Hotel hotel : hotels){
            int numberOfRooms = generateRandomRoomNumber();
            List<Room> roomsToAdd = new ArrayList<>();
            
            for(int i = 1; i<=numberOfRooms; i++){
                roomsToAdd.add(new Room(i, generateRandomNumberOfBeds()));
            }
            
            hotel.setRooms(roomsToAdd);
        }
        hotelRepository.saveAll(hotels);
                
        // Save hotels
        return hotels;
    }


    public List<Reservation> generateReservations(List<Guest> guests, List<Hotel> hotels) {
        List<Reservation> reservations = new ArrayList<>();
        LocalDate reservationDate = LocalDate.now();

        for (Guest guest : guests) {
            for (int i = 0; i < 5; i++) {
                // Add reservations for the first two hotels
                Reservation reservation1 = new Reservation(guest, hotels.get(0).getRooms().get(0), reservationDate);
                reservations.add(reservation1);
                
                reservationDate = reservationDate.plusDays(1);
            }
        }

        reservationRepository.saveAll(reservations);

        return reservations;
    }



    public int generateRandomRoomNumber() {
        // Create an instance of the Random class
        Random rand = new Random();

        // Generate a random room number between 10 and 40 (inclusive)
        int minRoomNumber = 10;
        int maxRoomNumber = 40;
        return rand.nextInt(maxRoomNumber - minRoomNumber + 1) + minRoomNumber;
    }

    public int generateRandomNumberOfBeds() {
        // Create an instance of the Random class
        Random rand = new Random();

        // Generate a random room number between 10 and 40 (inclusive)
        int minRoomNumber = 1;
        int maxRoomNumber = 4;
        return rand.nextInt(maxRoomNumber - minRoomNumber + 1) + minRoomNumber;
    }

    public int generateRandomZipNumber() {
        // Create an instance of the Random class
        Random rand = new Random();

        // Generate a random room number between 10 and 40 (inclusive)
        int minRoomNumber = 1000;
        int maxRoomNumber = 9999;
        return rand.nextInt(maxRoomNumber - minRoomNumber + 1) + minRoomNumber;
    }

    List<String> dummyHotelNames = new ArrayList<>(Arrays.asList(
            "Sunset Paradise Hotel", "Golden Sands Resort", "City Lights Inn", "Mountain View Lodge",
            "Ocean Breeze Inn", "Palm Grove Hotel", "Serenity Suites", "Royal Palace Hotel",
            "Majestic View Resort", "Tranquil Retreat Inn", "Silver Lake Hotel", "Whispering Pines Lodge",
            "Eagle's Nest Resort", "Starlight Plaza Hotel", "Harbor View Inn", "Grand Central Hotel",
            "Emerald Isle Resort", "Hidden Oasis Lodge", "Skyline Towers Hotel", "Crystal Waters Inn",
            "Sunrise Plaza Hotel", "Blue Lagoon Resort", "Urban Oasis Lodge", "Coral Cove Hotel",
            "Moonlight Gardens Inn", "Royal Oak Resort", "Mystic Springs Hotel", "Tropical Paradise Inn",
            "Riverfront Plaza Hotel", "Mountain Lodge Retreat", "Azure Skies Resort", "Golden Gate Hotel",
            "Diamond View Inn", "Island Escape Lodge", "Aurora Heights Hotel", "Wharfside Plaza Resort",
            "Lush Valley Retreat", "Silent Meadows Hotel", "Sapphire Shores Inn", "Highland Haven Lodge",
            "Crimson Sky Resort", "Siesta Sands Hotel", "Enchanted Woods Inn", "Silver Creek Lodge",
            "Harmony Heights Resort", "Pinehurst Plaza Hotel", "Eternal Springs Inn", "Midnight Sun Lodge",
            "Cascading Falls Hotel", "Marina Vista Resort", "Golden Ridge Inn", "Maple Grove Hotel",
            "Victorian Charm Lodge", "Seaside Serenity Inn", "Cobblestone Plaza Hotel", "Wildflower Retreat",
            "Central Park Resort", "Blue Ridge Haven Lodge", "Amber Sunset Hotel", "Nature's Trail Inn",
            "Reflections Plaza Hotel", "Lakeside Serenade Resort", "Crimson Peak Lodge", "Sunny Side Inn",
            "Urban Jungle Hotel", "Twilight Meadows Retreat", "Whispering Pines Lodge", "Cozy Corner Inn",
            "Oceanfront Oasis Hotel", "Riverbank Retreat", "Majestic Moonlight Resort", "Crystal Clear Inn",
            "Sunflower Fields Hotel", "Tranquil Harbor Lodge", "Royal Oak Resort", "Secret Garden Inn",
            "Mountain Majesty Hotel", "Pacific Pearl Resort", "Eternal Flame Lodge", "Jungle Paradise Inn",
            "Mystic Falls Hotel", "Golden Horizon Lodge", "Cottonwood Creek Resort", "Silver Birch Inn",
            "Sunset Serenade Hotel", "Enchanted Valley Retreat", "Ocean Mist Inn", "Cascading Rapids Lodge",
            "Palm Tree Plaza Hotel", "Sapphire Skies Resort", "Highland Mist Inn", "Eagle's Nest Hotel",
            "Cityscape Retreat", "Whispering Woods Lodge", "Emerald Isles Resort", "Urban Oasis Hotel",
            "Moonlight Bay Plaza", "Golden Gate Lodge", "Majestic View Hotel", "Lighthouse Inn",
            "Sunset Horizon Resort", "Eternal Springs Lodge", "Tropical Paradise Hotel", "Serenity Suites Inn",
            "Mountain View Plaza", "Ocean Breeze Lodge", "Island Retreat Hotel", "Crimson Sky Resort",
            "Silver Lake Inn", "Wharfside Plaza Lodge", "Skyline Towers Resort", "Riverfront Oasis Hotel",
            "Hidden Valley Lodge", "Blue Lagoon Inn", "Urban Escape Resort", "Sapphire Cove Hotel",
            "Golden Ridge Plaza", "Sunrise Splendor Lodge", "Palm Grove Resort", "Coral Cove Inn",
            "Moonlit Oasis Hotel", "City Lights Lodge", "Mountain Majesty Plaza", "Tranquil Retreat Resort",
            "Harbor View Inn", "Crystal Waters Hotel", "Eagle's Nest Plaza", "Starlight Paradise Lodge",
            "Sapphire Shores Inn", "Golden Sands Resort", "Central Park Retreat", "Maple Grove Lodge",
            "Victorian Charm Inn", "Siesta Sands Hotel", "Cobblestone Plaza Resort", "Wildflower Inn",
            "Marina Vista Hotel", "Golden Ridge Lodge", "Nature's Trail Plaza", "Lakeside Serenade Resort",
            "Crimson Peak Inn", "Sunny Side Hotel", "Urban Jungle Lodge", "Whispering Pines Resort",
            "Cozy Corner Plaza", "Oceanfront Oasis Inn", "Riverbank Retreat", "Majestic Moonlight Lodge",
            "Crystal Clear Hotel", "Sunflower Fields Plaza", "Tranquil Harbor Resort", "Secret Garden Lodge",
            "Mountain Majesty Hotel", "Pacific Pearl Plaza", "Eternal Flame Inn", "Jungle Paradise Resort",
            "Mystic Falls Hotel", "Golden Horizon Lodge", "Cottonwood Creek Plaza", "Silver Birch Lodge",
            "Sunset Serenade Hotel", "Enchanted Valley Resort", "Ocean Mist Inn", "Cascading Rapids Lodge",
            "Palm Tree Plaza Hotel", "Sapphire Skies Resort", "Highland Mist Inn", "Eagle's Nest Hotel",
            "Cityscape Retreat", "Whispering Woods Lodge", "Emerald Isles Resort", "Urban Oasis Hotel",
            "Moonlight Bay Plaza", "Golden Gate Lodge", "Majestic View Hotel", "Lighthouse Inn", "Sunset Paradise Hotel",
            "Wharfside Plaza Lodge", "Skyline Towers Resort", "Riverfront Oasis Hotel",
            "Hidden Valley Lodge", "Blue Lagoon Inn", "Urban Escape Resort", "Sapphire Cove Hotel",
            "Golden Ridge Plaza", "Sunrise Splendor Lodge", "Palm Grove Resort", "Coral Cove Inn",
            "Moonlit Oasis Hotel", "City Lights Lodge", "Mountain Majesty Plaza", "Tranquil Retreat Resort",
            "Harbor View Inn", "Crystal Waters Hotel", "Eagle's Nest Plaza", "Starlight Paradise Lodge",
            "Sapphire Shores Inn", "Golden Sands Resort", "Central Park Retreat", "Maple Grove Lodge",
            "Victorian Charm Inn", "Siesta Sands Hotel", "Cobblestone Plaza Resort", "Wildflower Inn",
            "Marina Vista Hotel", "Golden Ridge Lodge", "Nature's Trail Plaza", "Lakeside Serenade Resort",
            "Crimson Peak Inn", "Sunny Side Hotel", "Urban Jungle Lodge", "Whispering Pines Resort",
            "Cozy Corner Plaza", "Oceanfront Oasis Inn", "Riverbank Retreat", "Majestic Moonlight Lodge",
            "Wharfside Plaza Lodge", "Skyline Towers Resort", "Riverfront Oasis Hotel",
            "Hidden Valley Lodge", "Blue Lagoon Inn", "Urban Escape Resort", "Sapphire Cove Hotel",
            "Golden Ridge Plaza", "Sunrise Splendor Lodge", "Palm Grove Resort", "Coral Cove Inn",
            "Moonlit Oasis Hotel", "City Lights Lodge", "Mountain Majesty Plaza", "Tranquil Retreat Resort",
            "Harbor View Inn", "Crystal Waters Hotel", "Eagle's Nest Plaza", "Starlight Paradise Lodge",
            "Sapphire Shores Inn", "Golden Sands Resort", "Central Park Retreat", "Maple Grove Lodge",
            "Victorian Charm Inn", "Siesta Sands Hotel", "Cobblestone Plaza Resort", "Wildflower Inn",
            "Marina Vista Hotel", "Golden Ridge Lodge", "Nature's Trail Plaza", "Lakeside Serenade Resort",
            "Crimson Peak Inn", "Sunny Side Hotel", "Urban Jungle Lodge", "Tranquil Harbor Resort"
    ));

    List<String> dummyStreetNames = new ArrayList<>(Arrays.asList(
            "Maple Street", "Oak Avenue", "Cedar Lane", "Pine Road", "Elm Boulevard",
            "Birch Lane", "Willow Drive", "Sycamore Avenue", "Aspen Road", "Hickory Lane",
            "Cypress Drive", "Juniper Avenue", "Chestnut Street", "Magnolia Lane", "Redwood Avenue",
            "Poplar Road", "Beech Boulevard", "Alder Lane", "Mulberry Drive", "Spruce Street",
            "Dogwood Lane", "Cherry Road", "Acacia Avenue", "Cottonwood Boulevard", "Catalpa Lane",
            "Hemlock Drive", "Linden Street", "Locust Lane", "Mahogany Road", "Palm Boulevard",
            "Peach Lane", "Pear Avenue", "Plum Street", "Pomegranate Drive", "Quince Lane",
            "Raspberry Road", "Saffron Avenue", "Sage Boulevard", "Tamarind Lane", "Thyme Drive",
            "Vanilla Street", "Verbena Lane", "Vetiver Road", "Violet Avenue", "Wintergreen Boulevard",
            "Yarrow Lane", "Ylang-Ylang Drive", "Zinnia Street", "Almond Lane", "Anise Avenue",
            "Basil Road", "Bay Leaf Lane", "Bergamot Street", "Borage Lane", "Calendula Avenue",
            "Cardamom Road", "Carrot Lane", "Chervil Street", "Cilantro Boulevard", "Cinnamon Lane",
            "Clover Avenue", "Coriander Road", "Dill Lane", "Echinacea Street", "Fennel Lane",
            "Garlic Avenue", "Ginger Road", "Hibiscus Lane", "Hyssop Street", "Jasmine Boulevard",
            "Kale Lane", "Lavender Avenue", "Lemon Road", "Licorice Lane", "Marjoram Street",
            "Mint Lane", "Mustard Avenue", "Nutmeg Road", "Oregano Lane", "Paprika Street",
            "Parsley Lane", "Peppermint Avenue", "Rosemary Road", "Saffron Lane", "Sage Street",
            "Spearmint Lane", "Tarragon Avenue", "Thyme Road", "Turmeric Lane", "Vanilla Street",
            "Vetiver Lane", "Violet Road", "Wintergreen Lane", "Yarrow Avenue", "Ylang-Ylang Road",
            "Zinnia Lane", "Almond Street", "Anise Lane", "Basil Avenue", "Bay Leaf Lane",
            "Bergamot Road", "Borage Street", "Calendula Lane", "Cardamom Avenue", "Carrot Lane",
            "Chervil Street", "Cilantro Road", "Cinnamon Lane", "Clover Avenue", "Coriander Lane",
            "Dill Street", "Echinacea Lane", "Fennel Avenue", "Garlic Lane", "Ginger Street",
            "Hibiscus Avenue", "Hyssop Lane", "Jasmine Street", "Kale Avenue", "Lavender Lane",
            "Lemon Street", "Licorice Avenue", "Marjoram Lane", "Mint Street", "Mustard Lane",
            "Nutmeg Avenue", "Oregano Lane", "Paprika Street", "Parsley Lane", "Peppermint Avenue",
            "Rosemary Lane", "Saffron Street", "Sage Lane", "Spearmint Avenue", "Tarragon Lane",
            "Thyme Street", "Turmeric Lane", "Vanilla Avenue", "Vetiver Lane", "Violet Street",
            "Wintergreen Lane", "Yarrow Avenue", "Ylang-Ylang Lane", "Zinnia Street", "Maple Street",
            "Maple Street", "Oak Avenue", "Cedar Lane", "Pine Road", "Elm Boulevard",
            "Birch Lane", "Willow Drive", "Sycamore Avenue", "Aspen Road", "Hickory Lane",
            "Cypress Drive", "Juniper Avenue", "Chestnut Street", "Magnolia Lane", "Redwood Avenue",
            "Poplar Road", "Beech Boulevard", "Alder Lane", "Mulberry Drive", "Spruce Street",
            "Dogwood Lane", "Cherry Road", "Acacia Avenue", "Cottonwood Boulevard", "Catalpa Lane",
            "Hemlock Drive", "Linden Street", "Locust Lane", "Mahogany Road", "Palm Boulevard",
            "Peach Lane", "Pear Avenue", "Plum Street", "Pomegranate Drive", "Quince Lane",
            "Raspberry Road", "Saffron Avenue", "Sage Boulevard", "Tamarind Lane", "Thyme Drive",
            "Vanilla Street", "Verbena Lane", "Vetiver Road", "Violet Avenue", "Wintergreen Boulevard",
            "Yarrow Lane", "Ylang-Ylang Drive", "Zinnia Street", "Almond Lane", "Anise Avenue",
            "Basil Road", "Bay Leaf Lane", "Bergamot Street", "Borage Lane", "Calendula Avenue",
            "Cardamom Road", "Carrot Lane", "Chervil Street", "Cilantro Boulevard", "Cinnamon Lane",
            "Clover Avenue", "Coriander Road", "Dill Lane", "Echinacea Street", "Fennel Lane",
            "Garlic Avenue", "Ginger Road", "Hibiscus Lane", "Hyssop Street", "Jasmine Boulevard",
            "Kale Lane", "Lavender Avenue", "Lemon Road", "Licorice Lane", "Marjoram Street",
            "Mint Lane", "Mustard Avenue", "Nutmeg Road", "Oregano Lane", "Paprika Street",
            "Parsley Lane", "Peppermint Avenue", "Rosemary Road", "Saffron Lane", "Sage Street",
            "Spearmint Lane", "Tarragon Avenue", "Thyme Road", "Turmeric Lane", "Vanilla Street",
            "Vetiver Lane", "Violet Road", "Wintergreen Lane", "Yarrow Avenue", "Ylang-Ylang Road",
            "Zinnia Lane", "Almond Street", "Anise Lane", "Basil Avenue", "Bay Leaf Lane",
            "Bergamot Road", "Borage Street", "Calendula Lane", "Cardamom Avenue", "Carrot Lane", "Saffron Lane"
    ));

    List<String> dummyCityNames = new ArrayList<>(Arrays.asList(
            "New York", "Los Angeles", "Chicago", "Houston", "Phoenix",
            "Philadelphia", "San Antonio", "San Diego", "Dallas", "San Jose",
            "Austin", "Jacksonville", "San Francisco", "Indianapolis", "Columbus",
            "Fort Worth", "Charlotte", "Seattle", "Denver", "El Paso",
            "Detroit", "Washington", "Boston", "Memphis", "Nashville",
            "Portland", "Oklahoma City", "Las Vegas", "Baltimore", "Louisville",
            "Milwaukee", "Albuquerque", "Tucson", "Fresno", "Sacramento",
            "Kansas City", "Long Beach", "Mesa", "Atlanta", "Colorado Springs",
            "Virginia Beach", "Raleigh", "Omaha", "Miami", "Oakland",
            "Minneapolis", "Tulsa", "Wichita", "New Orleans", "Arlington",
            "Cleveland", "Bakersfield", "Tampa", "Aurora", "Honolulu",
            "Anaheim", "Santa Ana", "Corpus Christi", "Riverside", "St. Louis",
            "Lexington", "Stockton", "Pittsburgh", "Saint Paul", "Anchorage",
            "Cincinnati", "Henderson", "Greensboro", "Plano", "Newark",
            "Toledo", "Lincoln", "Orlando", "Chula Vista", "Jersey City",
            "Chandler", "Fort Wayne", "Buffalo", "Durham", "St. Petersburg",
            "Irvine", "Laredo", "Lubbock", "Madison", "Gilbert",
            "Norfolk", "Reno", "Winston-Salem", "Glendale", "Hialeah",
            "Garland", "Scottsdale", "Irving", "Chesapeake", "North Las Vegas",
            "Fremont", "Baton Rouge", "Richmond", "Boise", "San Bernardino",
            "Spokane", "Birmingham", "Modesto", "Des Moines", "Rochester",
            "Tacoma", "Fontana", "Oxnard", "Moreno Valley", "Fayetteville",
            "Huntington Beach", "Yonkers", "Aurora", "Glendale", "Shreveport",
            "Akron", "Little Rock", "Augusta", "Amarillo", "Mobile",
            "Grand Rapids", "Salt Lake City", "Huntsville", "Tallahassee", "Grand Prairie",
            "Overland Park", "Knoxville", "Worcester", "Brownsville", "Newport News",
            "Santa Clarita", "Port St. Lucie", "Providence", "Fort Lauderdale", "Chattanooga",
            "Tempe", "Oceanside", "Garden Grove", "Rancho Cucamonga", "Cape Coral",
            "Santa Rosa", "Vancouver", "Sioux Falls", "Peoria", "Ontario",
            "Jackson", "Elk Grove", "Springfield", "Pembroke Pines", "Salem",
            "Corona", "Eugene", "McKinney", "Fort Collins", "Lancaster",
            "Cary", "Palmdale", "Hayward", "Salinas", "Friscov",
            "Springfield", "Pasadena", "Macon", "Alexandria", "Pomona",
            "Lakewood", "Sunnyvale", "Escondido", "Kansas City", "Rockford",
            "Torrance", "Hollywood", "Joliet", "Bridgeport", "Clarksville",
            "Paterson", "Naperville", "Frisco", "Mesquite", "Savannah",
            "Syracuse", "Dayton", "Orange", "Fullerton", "Pasadena",
            "Hampton", "McAllen", "Killeen", "Warren", "Bellevue",
            "New Haven", "Sterling Heights", "West Valley City", "Columbia", "Olathe",
            "Thousand Oaks", "Cedar Rapids", "Cedar Rapids", "Topeka", "Visalia", "Waco", "Elizabeth",
            "Bellevue", "Gainesville", "Simi Valley", "Charleston", "Carrollton",
            "Coral Springs", "Stamford", "Hartford", "Concord", "Roseville",
            "Thornton", "Kent", "Lafayette", "Surprise", "Denton",
            "Victorville", "Evansville", "Santa Clara", "Abilene", "Athens",
            "Vallejo", "Allentown", "Norman", "Beaumont", "Independence",
            "Columbia", "Springfield", "El Monte", "Berkeley", "Peoria",
            "Norman", "High Point", "Richardson", "Downey", "Arvada",
            "Inglewood", "Clearwater", "Rochester", "Pueblo", "Lowell",
            "Wilmington", "Arvada", "Provo", "Waterbury", "Billings",
            "Round Rock", "Murfreesboro", "Lewisville", "Westminster"
    ));

        List<String> COUNTRY_NAMES = new ArrayList<>(Arrays.asList(
            "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda", "Argentina", "Armenia", "Australia",
            "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin",
            "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina Faso", "Burundi",
            "Cabo Verde", "Cambodia", "Cameroon", "Canada", "Central African Republic", "Chad", "Chile", "China", "Colombia",
            "Comoros", "Congo (Congo-Brazzaville)", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czechia (Czech Republic)",
            "Denmark", "Djibouti", "Dominica", "Dominican Republic", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea",
            "Eritrea", "Estonia", "Eswatini", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia",
            "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti",
            "Holy See", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy",
            "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon",
            "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Madagascar", "Malawi", "Malaysia",
            "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova",
            "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar (formerly Burma)", "Namibia", "Nauru", "Nepal",
            "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "North Korea", "North Macedonia (formerly Macedonia)",
            "Norway", "Oman", "Pakistan", "Palau", "Palestine State", "Panama", "Papua New Guinea", "Paraguay", "Peru",
            "Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russia", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia",
            "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal",
            "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa",
            "South Korea", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Sweden", "Switzerland", "Syria", "Tajikistan",
            "Tanzania", "Thailand", "Timor-Leste", "Togo", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan",
            "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States of America", "Uruguay", "Uzbekistan",
            "Vanuatu", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe", "Norway", "Oman", "Pakistan", "Palau", "Palestine State", "Panama", "Papua New Guinea", "Paraguay", "Peru",
            "Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russia", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia",
            "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal",
            "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa",
            "South Korea", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Sweden", "Switzerland", "Syria", "Tajikistan",
            "Tanzania", "Thailand", "Timor-Leste", "Togo", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan",
            "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States of America"
    ));

}
