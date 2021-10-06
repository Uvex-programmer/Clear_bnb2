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
        Property p = property.get();
        AddressLog addressLog = addressToLog(p.getAddress());
        ArrayList<AmenityLog> amenityLogs = amenityToLogList(p.getAmenities());
        ArrayList<ImageLog> imageLogs = imageToLogList(p.getImages());
        PropertyLog propertyLog = new PropertyLog();
        propertyLog.addProperty(p);
        propertyLog.setBathrooms(p.getBathrooms());
        propertyLog.setCreatedAt(p.getCreatedAt());
        propertyLog.setGuests(p.getGuests());
        propertyLog.setDescription(p.getDescription());
        propertyLog.setBeds(p.getBeds());
        propertyLog.setDailyPrice(p.getDailyPrice());
        propertyLog.setEndDate(p.getEndDate());
        propertyLog.setStartDate(p.getStartDate());
        propertyLog.setTitle(p.getTitle());
        propertyLog.addAddress(addressLog);
        propertyLog.addAmenities(amenityLogs);
        propertyLog.addImages(imageLogs);
        return propertyLog;
    }
}
