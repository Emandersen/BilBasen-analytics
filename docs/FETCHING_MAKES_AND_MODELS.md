# Fetching Makes and Models from BilBasen

## Overview

Makes and models are available through the filter options in the search API response. The API returns a hierarchical structure where models are nested within makes, and some models have variants.

## Structure

```
Make (e.g., "BMW")
  └─ Model (e.g., "3-Serie") 
      └─ Variant (e.g., "320d", "330i")
```

## How to Fetch All Makes

### Endpoint
```
GET https://www.bilbasen.dk/bas-srp-site/_next/data/{BUILD_ID}/brugt/bil.json
```

### Extract Makes from Response

Makes are located at:
```
.pageProps.dehydratedState.queries[0].state.data.filterOptions[1].filterOptions[]
```

Filter for the object where `key == "Make"`.

### Response Structure

```json
{
  "type": "MultiSelection",
  "title": "Mærke",
  "key": "Make",
  "optionValues": [
    {
      "name": "Abarth",
      "value": "Abarth"
    },
    {
      "name": "BMW",
      "value": "BMW"
    },
    ...
  ]
}
```

### Example cURL Command

```bash
# 1. Get current build ID
BUILD_ID=$(curl -s 'https://www.bilbasen.dk/brugt/bil' | grep -o '"buildId":"[^"]*"' | cut -d'"' -f4)

# 2. Fetch all makes
curl -s "https://www.bilbasen.dk/bas-srp-site/_next/data/${BUILD_ID}/brugt/bil.json" \
  | jq -r '.pageProps.dehydratedState.queries[0].state.data.filterOptions[1].filterOptions[] 
    | select(.key == "Make") 
    | .optionValues[] 
    | "\(.name)|\(.value)"'
```

### Total Makes Available
**110+ manufacturers** as of 2026-02-01

### Sample Makes
- Abarth
- Alfa Romeo
- Audi
- BMW
- Citroën
- Ferrari
- Ford
- Honda
- Jaguar
- Kia
- Lamborghini
- Mazda
- Mercedes-Benz
- Nissan
- Peugeot
- Porsche
- Renault
- Škoda
- Tesla
- Toyota
- Volkswagen
- Volvo

---

## How to Fetch Models for a Specific Make

### Endpoint
```
GET https://www.bilbasen.dk/bas-srp-site/_next/data/{BUILD_ID}/brugt/bil.json?Make={MAKE}
```

### Query Parameter
- `Make`: The make value (e.g., `BMW`, `Audi`, `Mercedes-Benz`)
- **Multiple Makes**: You can request multiple makes in one response by repeating the `Make` parameter:
  ```
  ?Make=BMW&Make=Audi&Make=Mercedes-Benz
  ```
  This returns models for all specified makes in a single API call, reducing the number of requests needed.

### Extract Models from Response

Models are located at:
```
.pageProps.dehydratedState.queries[0].state.data.filterOptions[1].filterOptions[]
```

Filter for the object where `key == "Model"`, then access `dependantOptionValues`.

### Response Structure

```json
{
  "type": "NestedMultiSelection",
  "title": "Model",
  "key": "Model",
  "dependantOptionValues": [
    {
      "parentKey": "Make",
      "parentValue": "BMW",
      "optionValues": [
        {
          "name": "1-Serie",
          "value": "ms-1-Serie",
          "optionValues": [
            {
              "name": "118i",
              "value": "118i"
            },
            {
              "name": "118d",
              "value": "118d"
            },
            ...
          ]
        },
        {
          "name": "3-Serie",
          "value": "ms-3-Serie",
          "optionValues": [
            {
              "name": "318i",
              "value": "318i"
            },
            {
              "name": "320d",
              "value": "320d"
            },
            {
              "name": "330i",
              "value": "330i"
            },
            ...
          ]
        },
        ...
      ]
    }
  ]
}
```

### Model Structure Types

#### 1. Model with Variants (Most Common)
Models like BMW 3-Serie have multiple variants (318i, 320d, etc.)

```json
{
  "name": "3-Serie",
  "value": "ms-3-Serie",
  "optionValues": [
    {"name": "318i", "value": "318i"},
    {"name": "320d", "value": "320d"}
  ]
}
```

#### 2. Model without Variants
Some models don't have sub-variants

```json
{
  "name": "1602",
  "value": "1602"
}
```

### Example cURL Command

```bash
# 1. Get current build ID
BUILD_ID=$(curl -s 'https://www.bilbasen.dk/brugt/bil' | grep -o '"buildId":"[^"]*"' | cut -d'"' -f4)

# 2. Fetch models for BMW
curl -s "https://www.bilbasen.dk/bas-srp-site/_next/data/${BUILD_ID}/brugt/bil.json?Make=BMW" \
  | jq '.pageProps.dehydratedState.queries[0].state.data.filterOptions[1].filterOptions[] 
    | select(.key == "Model") 
    | .dependantOptionValues[0].optionValues[] 
    | .name'

# 3. Fetch models with variants
curl -s "https://www.bilbasen.dk/bas-srp-site/_next/data/${BUILD_ID}/brugt/bil.json?Make=BMW" \
  | jq '.pageProps.dehydratedState.queries[0].state.data.filterOptions[1].filterOptions[] 
    | select(.key == "Model") 
    | .dependantOptionValues[0].optionValues[] 
    | {model: .name, variants: [.optionValues[]?.name] // []}'

# 4. Fetch models for MULTIPLE makes in one request
curl -s "https://www.bilbasen.dk/bas-srp-site/_next/data/${BUILD_ID}/brugt/bil.json?Make=BMW&Make=Audi&Make=Mercedes-Benz" \
  | jq '.pageProps.dehydratedState.queries[0].state.data.filterOptions[1].filterOptions[] 
    | select(.key == "Model") 
    | .dependantOptionValues[] 
    | {make: .parentValue, models: [.optionValues[] | .name]}'
```

---

## Complete Examples

### Example 1: Get All Makes and Save to File

```bash
#!/bin/bash

# Get build ID
BUILD_ID=$(curl -s 'https://www.bilbasen.dk/brugt/bil' | grep -o '"buildId":"[^"]*"' | cut -d'"' -f4)

# Fetch and save makes
curl -s "https://www.bilbasen.dk/bas-srp-site/_next/data/${BUILD_ID}/brugt/bil.json" \
  | jq '.pageProps.dehydratedState.queries[0].state.data.filterOptions[1].filterOptions[] 
    | select(.key == "Make") 
    | .optionValues' \
  > makes.json

echo "Saved $(jq 'length' makes.json) makes to makes.json"
```

### Example 2: Get Models for Multiple Makes Efficiently

```bash
#!/bin/bash

BUILD_ID=$(curl -s 'https://www.bilbasen.dk/brugt/bil' | grep -o '"buildId":"[^"]*"' | cut -d'"' -f4)

# Batch makes into groups to reduce API calls
makes=("BMW" "Audi" "Mercedes-Benz" "Volkswagen" "Toyota" "Ford" "Volvo" "Peugeot")
batch_size=5

for ((i=0; i<${#makes[@]}; i+=batch_size)); do
  batch=("${makes[@]:i:batch_size}")
  make_params=$(printf "&Make=%s" "${batch[@]}")
  make_params=${make_params:1}  # Remove leading &
  
  echo "Fetching models for: ${batch[*]}"
  
  curl -s "https://www.bilbasen.dk/bas-srp-site/_next/data/${BUILD_ID}/brugt/bil.json?${make_params}" \
    | jq '.pageProps.dehydratedState.queries[0].state.data.filterOptions[1].filterOptions[] 
      | select(.key == "Model") 
      | .dependantOptionValues[] 
      | {
          make: .parentValue,
          models: [
            .optionValues[] 
            | {
                name: .name,
                value: .value,
                variants: [.optionValues[]?.name] // []
              }
          ]
        }' > "models_batch_${i}.json"
  
  # Rate limiting
  sleep 2
done
```

### Example 3: Python Script to Fetch All Data (Optimized with Batching)

```python
import requests
import json
import re
import time

def get_build_id():
    """Fetch the current build ID from BilBasen"""
    response = requests.get('https://www.bilbasen.dk/brugt/bil')
    match = re.search(r'"buildId":"([^"]+)"', response.text)
    return match.group(1) if match else None

def get_makes(build_id):
    """Fetch all vehicle makes"""
    url = f'https://www.bilbasen.dk/bas-srp-site/_next/data/{build_id}/brugt/bil.json'
    response = requests.get(url)
    data = response.json()
    
    filter_options = data['pageProps']['dehydratedState']['queries'][0]['state']['data']['filterOptions'][1]['filterOptions']
    make_filter = next(f for f in filter_options if f['key'] == 'Make')
    
    return make_filter['optionValues']

def get_models_batch(build_id, make_values):
    """Fetch models for multiple makes in one request"""
    url = f'https://www.bilbasen.dk/bas-srp-site/_next/data/{build_id}/brugt/bil.json'
    params = [('Make', make) for make in make_values]
    response = requests.get(url, params=params)
    data = response.json()
    
    filter_options = data['pageProps']['dehydratedState']['queries'][0]['state']['data']['filterOptions'][1]['filterOptions']
    model_filter = next(f for f in filter_options if f['key'] == 'Model')
    
    if model_filter['dependantOptionValues']:
        return model_filter['dependantOptionValues']
    return []

def main():
    # Get build ID
    build_id = get_build_id()
    print(f"Build ID: {build_id}")
    
    # Get all makes
    makes = get_makes(build_id)
    print(f"Found {len(makes)} makes")
    
    # Batch makes into groups of 10 to reduce API calls
    batch_size = 10
    all_data = []
    
    for i in range(0, len(makes), batch_size):
        batch = makes[i:i+batch_size]
        make_values = [m['value'] for m in batch]
        make_names = {m['value']: m['name'] for m in batch}
        
        print(f"Fetching models for batch {i//batch_size + 1} ({len(batch)} makes)...")
        
        # Get models for all makes in this batch
        dependant_options = get_models_batch(build_id, make_values)
        
        # Process each make's models
        for make_option in dependant_options:
            make_value = make_option['parentValue']
            make_name = make_names.get(make_value, make_value)
            
            make_data = {
                'make': make_name,
                'make_value': make_value,
                'models': []
            }
            
            for model in make_option['optionValues']:
                model_data = {
                    'name': model['name'],
                    'value': model['value'],
                    'variants': []
                }
                
                # Check if model has variants
                if 'optionValues' in model and model['optionValues']:
                    model_data['variants'] = [v['name'] for v in model['optionValues']]
                
                make_data['models'].append(model_data)
            
            all_data.append(make_data)
        
        # Rate limiting
        time.sleep(2)
    
    # Save to file
    with open('bilbasen_makes_models.json', 'w', encoding='utf-8') as f:
        json.dump(all_data, f, ensure_ascii=False, indent=2)
    
    print(f"Saved data for {len(all_data)} makes to bilbasen_makes_models.json")

if __name__ == '__main__':
    main()
```

---

## Important Notes

1. **Build ID Changes**: The build ID changes with each deployment. Always fetch it dynamically.

2. **Rate Limiting**: Add delays between requests (recommended 1-2 seconds) to avoid overwhelming the server.

3. **Batch Requests**: You can fetch models for multiple makes in a single request by using multiple `Make` parameters:
   - Example: `?Make=BMW&Make=Audi&Make=Mercedes-Benz`
   - This significantly reduces API calls (from 110+ down to ~10-20 batched requests)
   - Recommended batch size: 5-10 makes per request

4. **Data Freshness**: Makes and models can change as new vehicles are added or removed from the marketplace.

4. **Model Values**: Model values often have a prefix like `ms-` (e.g., `ms-3-Serie`) which is used in API calls.

5. **Nested Structure**: The model data uses `dependantOptionValues` which indicates it depends on the Make filter selection.

6. **Variants**: Not all models have variants. Check if `optionValues` exists before accessing.

7. **Language**: All names are in Danish (e.g., "Mærke" = Make, "Model" = Model).

---

## Alternative: Fetching from HTML

If the JSON API structure changes, you can also scrape the HTML dropdown menus:

```bash
# Fetch the main search page
curl -s 'https://www.bilbasen.dk/brugt/bil' | grep -o '<option[^>]*value="[^"]*"[^>]*>[^<]*</option>'
```

However, the JSON API method is preferred as it's:
- Faster
- More structured
- Includes metadata
- Less prone to parsing errors

---

**Last Updated:** 2026-02-01  
**Status:** ✅ Active
