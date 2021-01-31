# BrewFind
---

Design Document  
Austin Walder  
Tianzuo Huang

## Introduction 
Have you ever been to a new city or region and thought, "Where can I get a drink around here?" With BrewFind you can:

-Find the breweries/bottleshops/cideries near your location.

-Search by region to plan your stops for your next trip.

-Search by venues that are dog-friendly, have a patio, or other accomodations you may need.

-Sort by how large the business is; from microbreweries up to large breweries.

Also you can keep a log of which breweries you have visited so you can tell other people about your favorites.

## Storyboard

[BrewFind Storyboard](https://projects.invisionapp.com/share/VF102Q8KRP3Z#/screens/443685950)


![BrewFindNearyou](https://user-images.githubusercontent.com/46360340/106367013-a7732d00-630d-11eb-8db2-4c8452475fac.png)

## Functional Requirements

### Requirement 100.0: Search for breweries

#### Scenario

As a user interested in drinking, I want to be able to see breweries around me based on my location. 

#### Dependencies

Breweries location search data are available and accessible.
The device has GPS capabilities, and the user has granted location access.

#### Assumptions

Cideries/Breweries/Bottleshops are distiguishable from each other.

#### Examples
1.1  

**Given** Breweries location search data is available  
**Given** User location data is available 

**When**  View the "Around You" tab

**Then** I should see a list of breweries/cideries/bottleshops sorted by distance from my location in ascending order. 

### Requirement 101: Search for breweries in a specific region

#### Scenario

As a user interested in planning brewery visits in another area, I want to be able to search for breweries by region.

#### Dependencies

Breweries location search data are available and accessible.

#### Assumptions

Breweries/Cideries/Bottleshops are distiguishable from each other.

#### Examples
1.1  

**Given** Breweries location search data is available  
**Given** User location data is available 

**When**  Change search type to city and search for "Cincinnati"  

**Then** I should receive see a list of breweries in Cincinnati sorted by name ascending.

1.2

**Given** Breweries location search data is available  
**Given** User location data is available 

**When**  Change search type to state and search for "California"  

**Then** I should receive see a list of breweries in California sorted by name ascending.

1.3  
**Given** Breweries location search data is available  
**Given** User location data is available 

**When** I change the search type to city or state and search for “huahhudnwjnajkb:qwe”  

**Then** I should receive zero results (an empty list)


### Requirement 102: Search For and Sort Breweries by Size

#### Scenario

As a user interested in visiting small local brewers in my area I want to sort breweries around me by size.

#### Dependencies

Breweries location search data are available and accessible.

#### Assumptions

Breweries/Cideries/Bottleshops are distiguishable from each other.

#### Examples
1.1  

**Given** Breweries location search data is available  
**Given** User location data is available 

**When**  I navigate to the search tab, select search by city, type "Cincinnati" in the search bar, and select "Size" in the sort tab  

**Then** I should receive see a list of breweries in Cincinnati sorted by size.

1.2

**Given** Breweries location search data is available  
**Given** User location data is available 

**When**  I navigate to the search tab, select search by state, type "Ohio" in the search bar, and select "Size" in the sort tab  

**Then** I should receive see a list of breweries in Ohio sorted by size.

1.3  
**Given** Breweries location search data is available  
**Given** User location data is available 

**When** I change the search type to city or state and search for “huahhudnwjnajkb:qwe” and sort by size  

**Then** I should receive zero results (an empty list)





