package com.example.ecomerceapp.model.product_models.enums
enum class ProductCategory(val displayName: String) {
    // Electronics
    MOBILE_PHONES("Mobile Phones"),
    PHONE_ACCESSORIES("Phone Accessories"),
    COMPUTERS("Computers and Laptops"),
    COMPUTER_ACCESSORIES("Computer and Network Accessories"),
    TABLETS("Tablets"),
    CAMERAS("Cameras and Photography"),
    AUDIO_EQUIPMENT("Audio and Speakers"),
    TELEVISIONS("Televisions"),
    GAMING("Gaming and Video Games"),
    SMART_HOME("Smart Home Devices"),

    // Fashion
    MEN_CLOTHING("Men's Clothing"),
    WOMEN_CLOTHING("Women's Clothing"),
    KIDS_CLOTHING("Kids and Baby Clothing"),
    SHOES("Shoes"),
    BAGS("Bags and Backpacks"),
    WATCHES("Watches"),
    JEWELRY("Jewelry"),
    FASHION_ACCESSORIES("Fashion Accessories"),
    ETHIOPIAN_TRADITIONAL_CLOTHING("Ethiopian Traditional Clothing"),

    // Beauty
    SKINCARE("Skincare"),
    HAIRCARE("Haircare"),
    MAKEUP("Makeup and Cosmetics"),
    PERFUMES("Perfumes and Fragrances"),
    PERSONAL_CARE("Personal Care and Grooming"),

    // Home
    FURNITURE("Furniture"),
    HOME_DECOR("Home Decor and Lighting"),
    KITCHENWARE("Kitchenware and Cooking Equipment"),
    BEDDING("Bedding and Linen"),
    HOME_APPLIANCES("Home Appliances"),
    CLEANING_SUPPLIES("Cleaning Supplies"),
    GARDENING("Gardening"),
    TOOLS_AND_HARDWARE("Tools and Hardware"),
    BUILDING_MATERIALS("Building Materials"),

    // Food and drink
    FOOD_AND_GROCERIES("Food and Groceries"),
    COFFEE_AND_TEA("Coffee and Tea"),
    SPICES_AND_SEASONINGS("Spices and Seasonings"),
    GRAINS_AND_FLOUR("Teff, Grains and Flour"),
    BREAD_AND_BAKERY("Bread, Injera and Bakery"),
    DAIRY_AND_HONEY("Dairy, Butter and Honey"),
    MEAT_AND_FISH("Meat, Poultry and Fish"),
    FRUITS_AND_VEGETABLES("Fruits and Vegetables"),
    SNACKS_AND_SWEETS("Snacks and Sweets"),
    BEVERAGES("Beverages"),
    ETHIOPIAN_TRADITIONAL_FOOD("Ethiopian Traditional Food and Drinks"),

    // Agriculture
    AGRICULTURAL_PRODUCTS("Agricultural Products"),
    FARMING_EQUIPMENT("Farming Tools and Equipment"),
    SEEDS_AND_FERTILIZERS("Seeds and Fertilizers"),
    LIVESTOCK("Livestock and Poultry"),
    ANIMAL_FEED("Animal Feed"),
    BEEKEEPING("Beekeeping Supplies"),

    // Ethiopian handmade and cultural
    ETHIOPIAN_HANDICRAFTS("Ethiopian Handicrafts"),
    ETHIOPIAN_ART("Ethiopian Art and Paintings"),
    ETHIOPIAN_TEXTILES("Ethiopian Textiles and Rugs"),
    LEATHER_GOODS("Leather Goods"),
    RELIGIOUS_PRODUCTS("Religious Products"),
    SOUVENIRS_AND_GIFTS("Gifts and Souvenirs"),

    // Books, office, hobbies
    BOOKS("Books"),
    SCHOOL_AND_OFFICE_SUPPLIES("School and Office Supplies"),
    ART_AND_CRAFT_SUPPLIES("Art and Craft Supplies"),
    MUSICAL_INSTRUMENTS("Musical Instruments"),
    COLLECTIBLES_AND_ANTIQUES("Collectibles and Antiques"),

    // Sports and kids
    SPORTS_AND_FITNESS("Sports and Fitness"),
    OUTDOOR_GEAR("Outdoor and Camping Gear"),
    BICYCLES("Bicycles and Accessories"),
    BABY_PRODUCTS("Baby Products"),
    TOYS_AND_GAMES("Toys and Board Games"),

    // Health
    HEALTH_AND_WELLNESS("Health and Wellness"),
    MEDICAL_EQUIPMENT("Medical Equipment"),

    // Vehicles
    CARS("Cars"),
    MOTORCYCLES("Motorcycles"),
    VEHICLE_PARTS("Vehicle Parts and Accessories"),

    // Business
    INDUSTRIAL_EQUIPMENT("Industrial Equipment"),
    ELECTRICAL_AND_PLUMBING("Electrical and Plumbing Supplies"),
    SAFETY_EQUIPMENT("Safety Equipment"),
    BUSINESS_EQUIPMENT("Business and Commercial Equipment"),

    // Other
    PET_SUPPLIES("Pet Supplies"),
    SERVICES("Services"),
    OTHER("Other")
}