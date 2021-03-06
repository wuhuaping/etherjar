package org.ethereumclassic.etherjar.model

import spock.lang.Specification

/**
 *
 * @since
 * @author Igor Artamonov
 */
class HexDataSpec extends Specification {

    def "Parse hex"() {
        expect:
        HexData.from(hex).bytes == bytes
        HexData.from(hex).toString() == hex.toLowerCase()
        where:
        hex         | bytes
        '0xff'      | [-1] as byte[]
        '0x00'      | [0] as byte[]
        '0x01'      | [1] as byte[]
        '0x0f'      | [15] as byte[]
        '0x0001'    | [0, 1] as byte[]
        '0xff01'    | [-1, 1] as byte[]
        '0x000001'  | [0, 0, 1] as byte[]
        '0x000000'  | [0, 0, 0] as byte[]
        '0xffffff'  | [-1, -1, -1] as byte[]
        '0xABcD'    | [-85, -51] as byte[]
    }

    def "Throw on invalid value"() {
        when:
        HexData.from('')
        then:
        thrown(IllegalArgumentException)

        when:
        new HexData(null as byte[])
        then:
        thrown(IllegalArgumentException)

        when:
        HexData.from(null as String)
        then:
        thrown(IllegalArgumentException)

        when:
        HexData.from('0xfake')
        then:
        thrown(IllegalArgumentException)
    }
}
