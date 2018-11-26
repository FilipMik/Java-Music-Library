package cz.muni.fi.pa165.music_library.service;

import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Peter Žiška
 * ID: 487569
 */

@Service
public class TimeServiceImpl implements TimeService {

    @Override
    public Date getCurrentDate() {
        return new Date();
    }
}
