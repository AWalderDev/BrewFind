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

As a user interested in drinking, I want to be able to search breweries around me base on my location. 

#### Dependencies

Breweries location search data are available and accessible.  

#### Assumptions

Scientific names are stated in Latin.  

Common names are stated in English.  

#### Examples
1.1  

**Given** Breweries location search data is available  

**When**  I search for “Breweries”  

**Then** I should receive at least one result with these attributes:  

Breweries: Any breweriese nearby my location  

Bottleshops: Any bottleshops with the name "breweries" and nearby my location 

Cideries: Any cideries with the name "breweries" and nearby my location  

1.2  
**Given** Breweries location search data is available

**When** I search for “huahhudnwjnajkb:qwe”  

**Then** I should receive zero results (an empty list)


