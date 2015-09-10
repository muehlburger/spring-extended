package org.springframework.extended.section08

import org.springframework.core.env.PropertySource

class CustomPropertySource extends PropertySource<Map<String, Object>> {


    CustomPropertySource() {
        super("CustomPropertySource", [propertyA: "valueA", propertyB: "valueB"])
    }

    @Override
    Object getProperty(String name) {
        Map<String, Object> source = getSource()
        return source.get(name)
    }
}
