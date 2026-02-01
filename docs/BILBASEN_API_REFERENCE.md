# Bilbasen.dk API Reference

## Table of Contents

1. [Overview](#overview)
2. [Authentication](#authentication)
3. [Base URL & Build ID](#base-url--build-id)
4. [Endpoints](#endpoints)
   - [Search Vehicles](#search-vehicles)
5. [Request Parameters](#request-parameters)
   - [Path Parameters](#path-parameters)
   - [Query Parameters](#query-parameters)
6. [Response Schema](#response-schema)
   - [Main Response](#main-response)
   - [Vehicle Listing Object](#vehicle-listing-object)
   - [Pagination Object](#pagination-object)
7. [Filter Reference](#filter-reference)
   - [Basic Filters](#basic-filters)
   - [Vehicle Characteristics](#vehicle-characteristics)
   - [Specifications](#specifications)
   - [Economy & Environment](#economy--environment)
   - [Electric/Hybrid](#electrichybrid)
   - [Location & Seller](#location--seller)
   - [Condition](#condition)
   - [Physical Details](#physical-details)
   - [Equipment](#equipment)
8. [Sort Options](#sort-options)
9. [Enumerations](#enumerations)
   - [Fuel Types](#fuel-types)
   - [Gear Types](#gear-types)
   - [Car Types](#car-types)
   - [Drive Wheel](#drive-wheel)
   - [Regions](#regions)
10. [Image URLs](#image-urls)
11. [Error Handling](#error-handling)
12. [Rate Limits](#rate-limits)

---

## Overview

Bilbasen.dk provides a JSON API through their Next.js application for searching and listing vehicles. The API is publicly accessible without authentication.

**API Type:** REST  
**Response Format:** JSON  
**Results Per Page:** 30

---

## Authentication

**None required** - API is publicly accessible

---

## Base URL & Build ID

**Base URL:**
```
https://www.bilbasen.dk/bas-srp-site/_next/data/{BUILD_ID}
```

**Current Build ID:** `6Jj2uRC1936wrPy1HoMOw`

**Note:** Build ID changes with each deployment and must be fetched dynamically from the HTML source of `https://www.bilbasen.dk/brugt/bil` by extracting the `buildId` field.

---

## Endpoints

### Search Vehicles

Search and filter vehicle listings.

**Endpoint:** `GET /brugt/bil[/{make}[/{model}]].json`

**Full URL Pattern:**
```
https://www.bilbasen.dk/bas-srp-site/_next/data/{BUILD_ID}/brugt/bil.json
https://www.bilbasen.dk/bas-srp-site/_next/data/{BUILD_ID}/brugt/bil/{make}.json
https://www.bilbasen.dk/bas-srp-site/_next/data/{BUILD_ID}/brugt/bil/{make}/{model}.json
```

---

## Request Parameters

### Path Parameters

| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| `make` | string | No | Vehicle manufacturer (lowercase, e.g., `bmw`, `mercedes-benz`) |
| `model` | string | No | Vehicle model (lowercase, e.g., `3-serie`, `c-klasse`). Requires `make` |

### Query Parameters

#### Pagination

| Parameter | Type | Description | Default |
|-----------|------|-------------|---------|
| `page` | integer | Page number | 1 |

#### Price

| Parameter | Type | Description |
|-----------|------|-------------|
| `pricefrom` | integer | Minimum price in DKK |
| `priceto` | integer | Maximum price in DKK |

#### Year

| Parameter | Type | Description |
|-----------|------|-------------|
| `yearfrom` | integer | Minimum model year |
| `yearto` | integer | Maximum model year |

#### Mileage

| Parameter | Type | Description |
|-----------|------|-------------|
| `mileagefrom` | integer | Minimum mileage in kilometers |
| `mileageto` | integer | Maximum mileage in kilometers |

#### Vehicle Type

| Parameter | Type | Description | Values |
|-----------|------|-------------|--------|
| `fuel` | string | Fuel type | See [Fuel Types](#fuel-types) |
| `geartype` | string | Transmission type | See [Gear Types](#gear-types) |
| `cartype` | string | Body type | See [Car Types](#car-types) |
| `drivewheel` | string | Drive type | See [Drive Wheel](#drive-wheel) |
| `doors` | integer | Number of doors | 2, 3, 4, 5, 6+ |

#### Appearance

| Parameter | Type | Description |
|-----------|------|-------------|
| `color` | string | Vehicle color |

#### Location

| Parameter | Type | Description |
|-----------|------|-------------|
| `region` | string | Region in Denmark. See [Regions](#regions) |

#### Sorting

| Parameter | Type | Description | Values |
|-----------|------|-------------|--------|
| `sort` | string | Sort field | See [Sort Options](#sort-options) |
| `sortorder` | string | Sort direction | `asc`, `desc` |

---

## Response Schema

### Main Response

```
{
  pageProps: {
    dehydratedState: {
      queries: [
        {
          state: {
            data: {
              listings: Array<VehicleListing>
              filterOptions: Array<FilterOption>
              sortOptions: Array<SortOption>
              pagination: PaginationObject
              searchRequest: Object
              breadcrumbs: Array<Breadcrumb>
              hits: integer
              totalAbundance: integer
            }
          }
        }
      ]
    }
  }
}
```

### Vehicle Listing Object

| Field | Type | Description |
|-------|------|-------------|
| `externalId` | integer | Unique vehicle listing ID |
| `uri` | string | Full URL to vehicle detail page |
| `make` | string | Manufacturer name |
| `model` | string | Model name |
| `variant` | string | Specific variant/trim |
| `doors` | integer | Number of doors |
| `description` | string | Vehicle description |
| `media` | array | Array of image objects with `mediaType` and `url` |
| `price` | object | Price object (see below) |
| `location` | object | Location object with lat, lon, zipCode, city, region |
| `properties` | object | Vehicle properties (see below) |
| `details` | array | Array of display text items |
| `features` | array | List of vehicle features |
| `isFavorite` | boolean | User favorite status |
| `sellerType` | string | `Dealer` or `Private` |
| `saleType` | string | `Retail`, `Consignment`, `Facilitated` |

#### Price Object

| Field | Type | Description |
|-------|------|-------------|
| `price` | integer | Price in DKK |
| `displayPrice` | string | Formatted price with currency |
| `unit` | string | Currency unit |
| `originalPrice` | integer | Original price (if discounted) |
| `priceType` | string | `Retail`, `WithoutTax`, `Wholesale` |

#### Properties Object

| Property | Description |
|----------|-------------|
| `firstregistrationdate` | First registration date (month/year) |
| `mileage` | Total kilometers driven |
| `kml` | Fuel efficiency (km per liter) |
| `hk` | Horsepower |
| `moth` | Annual ownership tax (Ejerafgift) |
| `trailer` | Towing capacity |
| `geartype` | Transmission type |
| `fueltype` | Fuel type |

Each property contains `displayTextLong` and `displayTextShort` fields.

### Pagination Object

| Field | Type | Description |
|-------|------|-------------|
| `next` | object | Next page information with `link` and `number` |

---

## Filter Reference

**Total Filters Available:** 51

### Basic Filters

| Filter | Key | Type | Description |
|--------|-----|------|-------------|
| Free Text Search | `FreeText` | Text | Search across all fields |
| Ownership | `Ownership` | SingleSelection | Køb (Retail) / Leasing |
| Category | `Category` | SingleSelection | Car/Van/Bus/Truck/Autocamper |
| Make | `Make` | MultiSelection | 110+ manufacturers |
| Model | `Model` | NestedMultiSelection | Depends on make selection |
| Price Range | `PriceRange` | Range | Min/max price filter |
| Price Type | `PriceType` | MultiSelection | Retail/WithoutTax/Wholesale |
| New/Used | `NewUsed` | SingleSelection | New or used only |

### Vehicle Characteristics

| Filter | Key | Type | Options |
|--------|-----|------|---------|
| Car Type | `CarType` | MultiSelection | Micro, Stationcar, Suv, Cuv, Mpv, Sedan, Hatchback, Cabriolet, Coupe |
| Fuel Type | `FuelType` | MultiSelection | Electric, Benzin, Diesel, ElectricAndBenzin, ElectricAndDiesel, ElectricAndBenzinPlugin, ElectricAndDieselPlugin |
| Gear Type | `GearType` | MultiSelection | Manual, Automatic |
| Drive Wheel | `DriveWheel` | MultiSelection | Front, Back, Four |
| Doors | `Doors` | MultiSelection | 2, 3, 4, 5, 6+ |
| Color | `Color` | MultiSelection | 53 color options |

### Specifications

| Filter | Key | Type | Description |
|--------|-----|------|-------------|
| Model Year | `ModelYear` | Range | Model year range |
| First Registration | `FirstRegistration` | Range | Registration date range |
| Mileage | `Mileage` | Range | Kilometers driven |
| Horse Power | `HorsePower` | Range | Engine power |
| Torque | `Torque` | Range | Engine torque (Nm) |
| Motor Volume | `MotorVolumeCcm` | Range | Engine size (ccm) |
| Number of Cylinders | `NumberOfCylinders` | MultiSelection | 2-12+ cylinders |
| Acceleration | `ZeroToHundredAcceleration` | Numeric | 0-100 km/t time (seconds) |

### Economy & Environment

| Filter | Key | Type | Description |
|--------|-----|------|-------------|
| Km/Liter | `KmPerLiter` | Numeric | Fuel efficiency |
| Green Tax | `GreenTax` | Numeric | Annual ownership tax |
| CO2 Emission | `Co2Emission` | Numeric | CO2 emissions (g/km) |
| Euro Norm | `EuroNorm` | SingleSelection | Euro 1-6 emission standard |

### Electric/Hybrid

| Filter | Key | Type | Description |
|--------|-----|------|-------------|
| Electric Range | `ElectricRange` | Numeric | Electric range in kilometers |
| Battery Capacity | `BatteryCapacity` | Numeric | Battery capacity (kWh) |
| Charger Type | `ChargerType` | MultiSelection | CCS, CHAdeMO, Type2, Tesla |
| DC Charging Power | `ChargingPowerDc` | SingleSelection | Fast charging power |
| DC Charging Time | `ChargingTimeDc` | SingleSelection | Fast charging time |
| AC Charging Power | `ChargingPowerAc` | SingleSelection | Home charging power |

### Location & Seller

| Filter | Key | Type | Description |
|--------|-----|------|-------------|
| Zip Code | `ZipCode` | Numeric | Postal code |
| Region | `Region` | SingleSelection | 10 regions in Denmark |
| City | `City` | SingleSelection | 597 cities available |
| Distance to Seller | `DistanceToSeller` | Numeric | Distance in kilometers |
| Seller Types | `SellerTypes` | MultiSelection | Dealer, Private |
| Sale Type | `SaleType` | MultiSelection | Consignment, Facilitated |
| Created Within Days | `CreatedWithinDays` | SingleSelection | Listing age filter |

### Condition

| Filter | Key | Type | Description |
|--------|-----|------|-------------|
| Service OK | `ServiceOk` | Boolean | Service up to date |
| Newly MOT'd | `NewlyMot` | Boolean | Recently inspected |

### Physical Details

| Filter | Key | Type | Description |
|--------|-----|------|-------------|
| Seat Number | `SeatNumber` | MultiSelection | Number of seats |
| Trunk Size | `TrunkSize` | SingleSelection | Luggage capacity |
| Tow Bar | `TowBar` | MultiSelection | Has towbar |
| Towbar Min | `TowbarMin` | Numeric | Minimum towing weight |

### Equipment

| Filter | Key | Type | Item Count |
|--------|-----|------|------------|
| Interior Equipment | `InteriorEquipments` | MultiSelection | 34 options |
| Exterior Equipment | `ExteriorEquipments` | MultiSelection | 36 options |
| Safety Equipment | `SafetyEquipments` | MultiSelection | 39 options |
| Comfort Equipment | `ConfortEquipments` | MultiSelection | 47 options |
| Multimedia Equipment | `MultiMediaEquipments` | MultiSelection | 15 options |

---

## Sort Options

| Sort Field | Key | Order Options | Description |
|------------|-----|---------------|-------------|
| Standard | *(empty)* | - | Default relevance ranking |
| Price | `price` | asc, desc | Sort by price |
| Date | `date` | desc, asc | Sort by listing date |
| Model Year | `year` | desc, asc | Sort by model year |
| Mileage | `mileage` | desc, asc | Sort by kilometers |
| Km/l | `kml` | desc, asc | Sort by fuel efficiency |
| Electric Range | `electricmotorrange` | desc, asc | Sort by electric range |
| Battery Capacity | `batterycapacity` | desc, asc | Sort by battery capacity |
| Make | `make` | asc, desc | Sort alphabetically by manufacturer |
| Horse Power | `hk` | desc, asc | Sort by engine power |
| Trailer Weight | `trailer` | desc, asc | Sort by towing capacity |
| 0-100 km/t | `kmt` | desc, asc | Sort by acceleration |
| Green Tax | `moth` | desc, asc | Sort by annual tax |
| First Registration | `firstregistrationdate` | desc, asc | Sort by registration date |
| Distance to Seller | `distance` | asc, desc | Sort by proximity |

**Usage:** Combine `sort` and `sortorder` query parameters
```
?sort=price&sortorder=asc
```

---

## Enumerations

### Fuel Types

| Display Name | API Value |
|--------------|-----------|
| Electric | `Electric` |
| Benzin | `Benzin` |
| Diesel | `Diesel` |
| Hybrid - Benzin | `ElectricAndBenzin` |
| Hybrid - Diesel | `ElectricAndDiesel` |
| Plug-in - Benzin | `ElectricAndBenzinPlugin` |
| Plug-in - Diesel | `ElectricAndDieselPlugin` |

### Gear Types

| Display Name | API Value |
|--------------|-----------|
| Manuelt gear | `Manual` |
| Automatisk gear | `Automatic` |

### Car Types

| Display Name | API Value |
|--------------|-----------|
| Mikro | `Micro` |
| Stationcar | `Stationcar` |
| SUV | `Suv` |
| Crossover (CUV) | `Cuv` |
| Minibus (MPV) | `Mpv` |
| Sedan | `Sedan` |
| Hatchback | `Hatchback` |
| Cabriolet | `Cabriolet` |
| Coupe | `Coupe` |

### Drive Wheel

| Display Name | API Value |
|--------------|-----------|
| Forhjulstræk | `Front` |
| Baghjulstræk | `Back` |
| Firehjulstræk | `Four` |

### Regions

| Display Name | API Value |
|--------------|-----------|
| København | `københavn` |
| Syd- og Vestsjælland | `syd-vestsjælland` |
| Nordsjælland | `nordsjælland` |
| Bornholm | `bornholm` |
| Lolland-Falster | `lolland-falster` |
| Fyn | `fyn` |
| Syd- og Sønderjylland | `syd-sønderjylland` |
| Vestjylland | `vestjylland` |
| Nordjylland | `nordjylland` |
| Østjylland | `østjylland` |

---

## Image URLs

Vehicle images are served from a CDN with dynamic sizing.

**URL Pattern:**
```
https://billeder.bilbasen.dk/bilinfo/{UUID}.jpeg?class={SIZE}
```

**Available Sizes:**

| Size Code | Dimensions |
|-----------|------------|
| `S960X960` | 960×960px |
| `S640X640` | 640×640px |
| `S400X400` | 400×400px |

---

## Error Handling

### Common Errors

| Scenario | Description |
|----------|-------------|
| Invalid Build ID | Returns HTML page instead of JSON. Re-fetch build ID |
| 404 Not Found | Invalid endpoint or path parameters |
| 500 Server Error | Temporary server issue. Retry with backoff |

---

## Rate Limits

**Status:** Unknown - No official rate limits documented

**Recommendations:**
- Add 1-2 second delays between requests
- Implement exponential backoff on errors
- Use proper User-Agent header
- Cache build ID for session duration
- Monitor for HTTP 429 responses

---

## Notes & Limitations

1. Build ID changes with each deployment - must be fetched dynamically
2. Maximum 30 results per page - use pagination for more
3. API is publicly accessible without authentication
4. Individual vehicle detail pages use different architecture
5. Average response size: 500KB - 1MB
6. Total vehicle database: ~40,000-50,000 listings
7. Content primarily in Danish language

---

**Last Updated:** 2026-02-01  
**API Version:** Next.js  
**Status:** ✅ Active
