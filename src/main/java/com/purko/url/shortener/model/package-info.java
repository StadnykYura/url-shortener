@org.hibernate.annotations.GenericGenerator(
        name = Constants.ID_GENERATOR,
        strategy = "enhanced-sequence",
        parameters = {
                @org.hibernate.annotations.Parameter(
                        name = "sequence_name",
                        value = Constants.SHORTENER_SEQUENCE
                ),
                @org.hibernate.annotations.Parameter(
                        name = "initial_value",
                        value = "100000"
                )

        })
package com.purko.url.shortener.model;

import com.purko.url.shortener.util.Constants;