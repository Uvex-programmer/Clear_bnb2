package mapper;

import models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LogMapper {

    public AddressLog addressToLog(Address address){
        AddressLog addressLog = new AddressLog();
        addressLog.setCity(address.getCity());
        addressLog.setStreet(address.getStreet());
        addressLog.setZipcode(address.getZipcode());
        return addressLog;
    }

    public ArrayList<AmenityLog> amenityToLogList(List<Amenity> amenity){
        ArrayList<AmenityLog> aList = new ArrayList<>();
        for(Amenity a : amenity){
            aList.add(amenityToLogAmenity(a));
        }
        return aList;
    }

    public ArrayList<ImageLog> imageToLogList(List<Image> image){
        ArrayList<ImageLog> imgList = new ArrayList<>();
        for(Image img : image){
            imgList.add(imageToLogImage(img));
        }
        return imgList;
    }

    public ImageLog imageToLogImage(Image image){
        ImageLog imageLog = new ImageLog();
        imageLog.setPrimaryId(image.getPrimaryImage());
        imageLog.setUrl(image.getUrl());
        return imageLog;
    }

    public AmenityLog amenityToLogAmenity(Amenity amenity){
        AmenityLog amenity1 = new AmenityLog();
        amenity1.setAmenity(amenity.getAmenity());
        return amenity1;
    }

    public PropertyLog propertyToLog(Optional<Property> property){
        var address = addressToLog(property.get().getAddress());
        var amenity = amenityToLogList(property.get().getAmenities());
        var images = imageToLogList(property.get().getImages());
        PropertyLog propertyLog = new PropertyLog();
        propertyLog.addProperty(property.get());
        propertyLog.setBathrooms(property.get().getBathrooms());
        propertyLog.setCreatedAt(property.get().getCreatedAt());
        propertyLog.setGuests(property.get().getGuests());
        propertyLog.setDescription(property.get().getDescription());
        propertyLog.setBeds(property.get().getBeds());
        propertyLog.setDailyPrice(property.get().getDailyPrice());
        propertyLog.setEndDate(property.get().getEndDate());
        propertyLog.setStartDate(property.get().getStartDate());
        propertyLog.setTitle(property.get().getTitle());
        propertyLog.addAddress(address);
        propertyLog.addAmenities(amenity);
        propertyLog.addImages(images);
        return propertyLog;
    }
}
