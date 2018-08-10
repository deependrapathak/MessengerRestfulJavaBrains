package org.deep.pat.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.deep.pat.messenger.database.DatabaseClass;
import org.deep.pat.messenger.model.Profile;

public class ProfileService {
	
	private Map<String, Profile> profiles = DatabaseClass.getProfiles();
	
	public ProfileService() {
		profiles.put("Deep", new Profile(1L, "deeppathak", "Deependra", "Pathak"));
		profiles.put("Deepdup", new Profile(2L, "deeppathakdup", "Deependradup", "Pathakdup"));
	}
	
	public List<Profile> getAllProfiles(){
		
		return new ArrayList<>(profiles.values());
	}
	
	public Profile getProfile(String profileName) {
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size() + 1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile) {
		if(profile.getProfileName().isEmpty()) {
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}

	public void removeProfile(String profileName) {
		profiles.remove(profileName);
		
	}

}
