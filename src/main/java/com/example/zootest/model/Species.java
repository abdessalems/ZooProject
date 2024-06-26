package com.example.zootest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum Species {
    // Mammifères
    LION("Panthera leo"),
    TIGER("Panthera tigris"),
    ELEPHANT("Loxodonta africana"),
    GIRAFFE("Giraffa camelopardalis"),
    ZEBRA("Equus quagga"),
    MONKEY("Cercopithecidae"),
    KANGAROO("Macropus"),
    PANDA("Ailuropoda melanoleuca"),
    BEAR("Ursidae"),
    WOLF("Canis lupus"),
    DEER("Cervidae"),
    FOX("Vulpes vulpes"),
    RACCOON("Procyon lotor"),
    KOALA("Phascolarctos cinereus"),

    // Oiseaux
    PENGUIN("Spheniscidae"),
    EAGLE("Aquila chrysaetos"),
    PARROT("Psittaciformes"),
    OSTRICH("Struthio camelus"),
    PEACOCK("Pavo cristatus"),
    FLAMINGO("Phoenicopteridae"),
    OWL("Strigiformes"),
    SWAN("Cygnus"),
    SPARROW("Passeridae"),

    // Reptiles
    CROCODILE("Crocodylinae"),
    SNAKE("Serpentes"),
    TURTLE("Testudines"),
    LIZARD("Lacertilia"),
    CHAMELEON("Chamaeleonidae"),
    GECKO("Gekkota"),

    // Poissons
    SHARK("Selachimorpha"),
    CLOWNFISH("Amphiprioninae"),
    TUNA("Thunnini"),
    SALMON("Salmo salar"),
    GOLDFISH("Carassius auratus"),
    CATFISH("Siluriformes"),
    BARRACUDA("Sphyraena"),

    // Amphibiens
    FROG("Anura"),
    SALAMANDER("Caudata"),
    NEWT("Pleurodelinae"),
    TOAD("Bufonidae"),

    // Insectes
    BUTTERFLY("Lepidoptera"),
    BEE("Apis"),
    ANT("Formicidae"),
    LADYBUG("Coccinellidae"),
    DRAGONFLY("Anisoptera"),

    // Autres
    OCTOPUS("Octopoda"),
    JELLYFISH("Scyphozoa"),
    STARFISH("Asteroidea"),
    CORAL("Anthozoa");

    private final String scientificName;
}
